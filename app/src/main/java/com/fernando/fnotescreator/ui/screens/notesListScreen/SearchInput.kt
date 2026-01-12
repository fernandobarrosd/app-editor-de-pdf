package com.fernando.fnotescreator.ui.screens.notesListScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.fnotescreator.ui.theme.Red500
import com.fernando.fnotescreator.ui.theme.poppinsFontFamily

@Composable
fun SearchInput(
    value: String,
    isEnabled : Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (newValue: String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Red500,
            unfocusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.White
        ),
        enabled = isEnabled,

        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        placeholder = {
            Text(
                text = "Buscar notas",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                fontSize = 14.sp
            )
        },
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .focusRequester(focusRequester)
    )
}

@Preview
@Composable
private fun SearchInputPreviewWithEmptyValue() {
    SearchInput(value = "", isEnabled = true) {}
}

@Preview
@Composable
private fun SearchInputPreviewWithNotEmptyValue() {
    SearchInput(value = "value", isEnabled = true) {}
}