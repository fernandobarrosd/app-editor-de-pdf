package com.fernando.fnotescreator.ui.screens.noteInfoScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.Red400
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily

data class EditState(val icon: ImageVector, val text: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInfoScreen(
    noteID: String,
    onBackStack: () -> Unit) {
    val noteInfoViewModel = hiltViewModel<NoteInfoViewModel>()
    val note by noteInfoViewModel.note.observeAsState(null)
    var isEditable by remember { mutableStateOf(false) }
    var noteContent by remember(note) { mutableStateOf("") }
    val isUpdated by noteInfoViewModel.isUpdated.observeAsState(false)
    val editState = when (isEditable) {
        true -> EditState(Icons.Default.Close, "Cancelar")
        false -> EditState(Icons.Default.Edit, "Editar")
    }

    if (isUpdated) {
        onBackStack()
    }

    LaunchedEffect(noteID) {
        noteInfoViewModel.getNote(noteID)
    }

    LaunchedEffect(note) {
        note?.let { note ->
            noteContent = note.content
        }
    }

    Scaffold(
        floatingActionButton = {
            note?.let { note ->
                if (note.content != noteContent) {
                    Button(
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red400
                        ),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                        onClick = {
                            noteInfoViewModel.updateNote(noteContent)
                        }
                    ) {
                        Text(
                            text = "Atualizar",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    note?.let { note ->
                        Text(
                            text = note.name,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.width(300.dp)
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Navigation icon",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 18.dp)
                            .clickable {
                                onBackStack()
                            }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    note?.let { note ->
                        Log.i("IS_READ_ONLY", note.isReadOnly.toString())
                        if (note.isReadOnly) {
                            Text(
                                text = "Somente leitura",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                            )
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable {
                                        isEditable = !isEditable
                                    }
                            ) {
                                Text(
                                    text = editState.text,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.size(10.dp))
                                Icon(
                                    imageVector = editState.icon,
                                    contentDescription = "Edit icon",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            note?.let { note ->
                TextField(
                    value = noteContent,
                    onValueChange = { newValue ->
                        noteContent = newValue
                    },
                    enabled = if (note.isReadOnly) false else isEditable,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    ),
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Composable
private fun NoteInfoScreenPreview() {
    FNotesCreatorTheme {
        NoteInfoScreen("noteID") {}
    }
}
