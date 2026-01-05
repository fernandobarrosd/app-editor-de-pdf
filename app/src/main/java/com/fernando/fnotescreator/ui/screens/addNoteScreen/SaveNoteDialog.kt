package com.fernando.fnotescreator.ui.screens.addNoteScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.Green500
import com.fernando.fnotescreator.ui.theme.Red500
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily

@Composable
fun SaveNoteDialog(
    onSaveNote: (noteName: String) -> Unit,
    onCancelSaveNote: () -> Unit,
) {
    var noteName by remember { mutableStateOf("") }

    AlertDialog(
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        ),
        title = {
            Text(
                text = "Salvar nota",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        },
        text = {
            OutlinedTextField(
                value = noteName,
                onValueChange = { newValue ->
                    noteName = newValue
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium
                )

            )
        },
        containerColor = Color.White,
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = {
                    onSaveNote(noteName)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green500
                )
            ) {
                Text(
                    text = "Salvar",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onCancelSaveNote()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red500
                )
            ) {
                Text(
                    text = "Cancelar",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        },
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp)
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun SaveNoteDialogPreview() {
    FNotesCreatorTheme {
        Scaffold {
            Column(modifier = Modifier.fillMaxSize()) {
                SaveNoteDialog (
                    onSaveNote = {},
                    onCancelSaveNote = {},
                )
            }
        }
    }
}