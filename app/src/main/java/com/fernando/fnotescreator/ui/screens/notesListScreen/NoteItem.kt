package com.fernando.fnotescreator.ui.screens.notesListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
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
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.ui.theme.Red400
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID


@Composable
fun NoteItem(
    note: Note,
    onDeleteItem: () -> Unit,
    onSelectItem: () -> Unit) {
    val formattedCreatedAt = DateTimeFormatter
        .ofPattern("dd/MM/yyyy")
        .format(note.createdAt)

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .clickable {
                onSelectItem()
            }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.name,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(200.dp)
                )
                FilledIconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Red400
                    ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        onDeleteItem()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete icon",
                        tint = Color.White
                    )
                }

            }
            Spacer(Modifier.size(10.dp))
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
private fun NoteItemPreview() {
    NoteItem(
        note = Note(
            id = UUID.randomUUID().toString(),
            content = "Conteudo teste 1",
            createdAt = LocalDate.now(),
            isReadOnly = true,
            name = LoremIpsum().values.joinToString(" ")
        ),
        onDeleteItem = {},
        onSelectItem = {}
    )
}