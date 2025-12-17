package com.fernando.editordepdf.ui.screens.pdfsListScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.fernando.editordepdf.enums.PdfState
import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.ui.theme.EditorDePDFTheme
import com.fernando.editordepdf.ui.theme.Green500
import com.fernando.editordepdf.ui.theme.Red500
import com.fernando.editordepdf.ui.theme.poppinsFontFamily
import java.time.LocalDate
import java.util.UUID

@Composable
fun DeleteButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun DeletePdfInfoAlert(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    pdfInfo: PdfInfo
) {
    AlertDialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        containerColor = Color.White,
        onDismissRequest = {},
        confirmButton = {
            DeleteButton(
                text = "Sim",
                color = Green500,
                onClick = {
                    onConfirm()
                }
            )
        },
        dismissButton = {
            DeleteButton(
                text = "Não",
                color = Red500,
                onClick = {
                    onCancel()
                }
            )
        },
        title = {
            Text(
                text = "Deletar pdf ${pdfInfo.name}?",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = "Deseja deleta o Pdf",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400,
                color = Color.Black
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun DeletePdfInfoAlertPreview() {
    EditorDePDFTheme {
        Scaffold {
            Column(modifier = Modifier.fillMaxSize()) {
                DeletePdfInfoAlert(
                    onConfirm = {},
                    onCancel = {},
                    pdfInfo = PdfInfo(
                        id = UUID.randomUUID().toString(),
                        content = LoremIpsum().values.joinToString(" "),
                        createdAt = LocalDate.now(),
                        isReadOnly = true,
                        state = PdfState.IMPORTED,
                        name = "\"Pdf 1\""
                    )
                )
            }
        }
    }
}