package com.fernando.fnotescreator.ui.screens.notesListScreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.Red500
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily


@Composable
fun Warning() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Nenhuma nota, clique no \"+\" pra criar",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun NotesList(notes: List<Note>, onDeleteItem: (note: Note) -> Unit, onSelectItem: (noteID: String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp)
    ) {
        items(notes) { note ->
            NoteItem(
                note = note,
                onDeleteItem = {
                    onDeleteItem(note)
                },
                onSelectItem = {
                    onSelectItem(note.id)
                }
            )
            Spacer(Modifier.size(20.dp))
        }
    }
}

@Composable
fun Loading() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier.width(40.dp)
        )
    }
}

@Composable
fun NotesListScreen(
    onNavigateToNoteInfoScreen: (noteID: String) -> Unit,
    onNavigateToAddNoteScreen: () -> Unit) {
    val notesListViewModel = hiltViewModel<NotesListViewModel>()
    val notes by notesListViewModel.notes.observeAsState(emptyList())
    val isShowDeleteAlert by notesListViewModel.isShowDeleteAlert.observeAsState(false)
    val selectedNote by notesListViewModel.selectedNote.observeAsState(null)
    val isLoading by notesListViewModel.isLoading.observeAsState(false)
    val isSearched by notesListViewModel.isSearched.observeAsState(false)
    var noteName by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    notesListViewModel.getAllNotes()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(50.dp),
                containerColor = Red500,

                onClick = {
                    onNavigateToAddNoteScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { onTap ->
                        focusManager.clearFocus()
                    }
                }
        ) {
            if (isShowDeleteAlert) {
                selectedNote?.let { selectedNote ->
                    DeleteNoteAlert(
                        onConfirm = {
                            notesListViewModel.deleteNote()
                        },
                        onCancel = {
                            notesListViewModel.hideDeleteAlert()
                        },
                        note = selectedNote
                    )
                }
            }
            SearchInput(
                value = noteName,
                isEnabled = !isLoading,
                onValueChange = { newValue ->
                    noteName = newValue
                    if (noteName.isEmpty()) {
                        notesListViewModel.getAllNotes()
                        return@SearchInput
                    }
                    notesListViewModel.filterNotesByName(noteName)
                },
                modifier = Modifier
                    .padding(16.dp)
            )
            Spacer(Modifier.size(24.dp))
            if (isLoading) {
                Loading()
            }
            else if (!isSearched && notes.isEmpty()) {
                Warning()
            }
            else {
                NotesList(
                    notes = notes,
                    onDeleteItem = { note ->
                        notesListViewModel.showDeleteAlert(note)
                    },
                    onSelectItem = { noteID ->
                        onNavigateToNoteInfoScreen(noteID)
                    },
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PdfsListScreenPreview() {
    FNotesCreatorTheme {
        NotesListScreen(
            onNavigateToNoteInfoScreen = {},
            onNavigateToAddNoteScreen = {}
        )
    }
}