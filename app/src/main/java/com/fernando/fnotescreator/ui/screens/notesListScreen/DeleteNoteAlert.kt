package com.fernando.fnotescreator.ui.screens.notesListScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.ui.components.AlertButton
import com.fernando.fnotescreator.ui.theme.FNotesCreatorTheme
import com.fernando.fnotescreator.ui.theme.Green500
import com.fernando.fnotescreator.ui.theme.Red500
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily
import java.time.LocalDate
import java.util.UUID

@Composable
fun DeleteNoteAlert(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    note: Note
) {
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        containerColor = Color.White,
        onDismissRequest = {},
        confirmButton = {
            AlertButton(
                text = "Sim",
                color = Green500,
                onClick = {
                    onConfirm()
                }
            )
        },
        dismissButton = {
            AlertButton(
                text = "Não",
                color = Red500,
                onClick = {
                    onCancel()
                }
            )
        },
        title = {
            Text(
                text = "Deletar pdf \"${note.name}\"?",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        text = {
            Text(
                text = "Deseja deleta a nota?",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400,
                color = Color.Black
            )
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
private fun DeletePdfInfoAlertPreview() {
    FNotesCreatorTheme {
        Scaffold {
            Column(modifier = Modifier.fillMaxSize()) {
                DeleteNoteAlert(
                    onConfirm = {},
                    onCancel = {},
                    note = Note(
                        id = UUID.randomUUID().toString(),
                        content = LoremIpsum().values.joinToString(" "),
                        createdAt = LocalDate.now(),
                        name = "Nota teste"
                    )
                )
            }
        }
    }
}