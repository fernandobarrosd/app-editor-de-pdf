package com.fernando.editordepdf.ui.screens.pdfsListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.editordepdf.enums.PdfState
import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.ui.theme.Green500
import com.fernando.editordepdf.ui.theme.Red500
import com.fernando.editordepdf.ui.theme.Slate500
import com.fernando.editordepdf.ui.theme.poppinsFontFamily
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

enum class PdfInfoStateCardType(val color: Color, val title: String) {
    SAVED(Green500, "Salvo"),
    NOT_SAVED(Red500, "Não salvo"),
    IMPORTED(Slate500, "Importado")
}

@Composable
fun PdfInfoItem(
    pdfInfo: PdfInfo,
    onDeleteItem: () -> Unit,
    onSelectItem: () -> Unit) {
    val pdfInfoStateCardTypes = mapOf(
        PdfState.SAVED to PdfInfoStateCardType.SAVED,
        PdfState.NOT_SAVED to PdfInfoStateCardType.NOT_SAVED,
        PdfState.IMPORTED to PdfInfoStateCardType.IMPORTED
    )
    val pdfInfoStateCardType = pdfInfoStateCardTypes[pdfInfo.state]
    val formattedCreatedAt = DateTimeFormatter
        .ofPattern("dd/MM/yyyy")
        .format(pdfInfo.createdAt)

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .combinedClickable(
                onClick = {
                    onSelectItem()
                },
                onLongClick = {
                    onDeleteItem()
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pdfInfo.name,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(250.dp)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = pdfInfoStateCardType!!.color
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    )
                ) {
                    Text(
                        text = pdfInfoStateCardType.title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium,
                        softWrap = false,
                        fontSize = 12.sp
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = formattedCreatedAt,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black

                )
            }
        }
    }
}





@Preview
@Composable
private fun PdfInfoItemPreview() {
    PdfInfoItem(
        pdfInfo = PdfInfo(
            id = UUID.randomUUID().toString(),
            content = "Conteudo teste 1",
            createdAt = LocalDate.now(),
            isReadOnly = true,
            state = PdfState.IMPORTED,
            name = LoremIpsum().values.joinToString(" ")
        ),
        onDeleteItem = {},
        onSelectItem = {}
    )
}