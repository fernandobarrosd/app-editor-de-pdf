package com.fernando.fnotescreator.ui.screens.addNoteScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(onBackToNotesListScreen: () -> Unit) {
    val addNoteViewModel = hiltViewModel<AddNoteViewModel>()
    val isShowNoteNameInputDialog by addNoteViewModel.isShowNoteNameInputDialog
        .observeAsState(false)
    val isSaved by addNoteViewModel.isSaved.observeAsState(false)
    var isReadOnly by remember { mutableStateOf(false) }
    var noteContent by remember { mutableStateOf(LoremIpsum().values.joinToString(" ")) }

    if (isSaved) {
        onBackToNotesListScreen()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Editor",
                        color = Color.White,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                addNoteViewModel.showNoteNameInputDialog()
                            }
                    ) {
                        Text(
                            text = "Salvar",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.size(10.dp))
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Save icon",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isShowNoteNameInputDialog) {
            SaveNoteDialog(
                onSaveNote = { noteName ->

                    val noteDTO = SaveNoteDTO(noteName, noteContent, isReadOnly)
                    addNoteViewModel.saveNote(noteDTO)
                },
                onCancelSaveNote = {
                    addNoteViewModel.hideNoteNameInputDialog()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Somente leitura",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Checkbox(
                    checked = isReadOnly,
                    onCheckedChange = {
                        isReadOnly = it
                    }
                )
            }
            TextField(
                value = noteContent,
                onValueChange = { newValue ->
                    noteContent = newValue
                },
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

@Preview
@Composable
private fun AddPdfScreenPreview() {
    FNotesCreatorTheme {
        AddNoteScreen {}
    }
}