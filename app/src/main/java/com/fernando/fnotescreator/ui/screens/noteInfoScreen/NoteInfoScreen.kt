package com.fernando.fnotescreator.ui.screens.noteInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInfoScreen(
    noteID: String,
    onBackStack: () -> Unit) {
    val noteInfoViewModel = hiltViewModel<NoteInfoViewModel>()
    val note by noteInfoViewModel.note.observeAsState(null)
    var noteContent by remember(note) { mutableStateOf("") }


    LaunchedEffect(noteID) {
        noteInfoViewModel.getNote(noteID)
    }

    LaunchedEffect(note) {
        note?.let { note ->
            noteContent = note.content
        }
    }

    Scaffold(
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

            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            note?.let { note ->
                Text(
                    text = noteContent,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
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
