package com.fernando.editordepdf.ui.screens.pdfsListScreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.fernando.editordepdf.ui.theme.EditorDePDFTheme

/*val pdfsList = List(10) { index ->
    PdfInfo(
        id = UUID.randomUUID().toString(),
        content = "Conteudo teste ${index + 1}",
        createdAt = LocalDate.now(),
        isReadOnly = true,
        state = PdfState.NOT_SAVED,
        name = "name teste"
    )
}*/

@Composable
fun PdfsListScreen(
    paddingValues: PaddingValues,
    onNavigateToPdfInfoScreen: (pdfInfoId: String) -> Unit) {
    val pdfsListViewModel = hiltViewModel<PdfsListViewModel>()
    val pdfs by pdfsListViewModel.pdfs.observeAsState(emptyList())
    val isShowDeleteAlert by pdfsListViewModel.isShowDeleteAlert.observeAsState(false)
    val selectedPdfInfo by pdfsListViewModel.selectedPdfInfo.observeAsState(null)
    var pdfName by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
            selectedPdfInfo?.let { selectedPdfInfo ->
                DeletePdfInfoAlert(
                    onConfirm = {
                        pdfsListViewModel.deletePdfInfo()
                    },
                    onCancel = {
                        pdfsListViewModel.hideDeleteAlert()
                    },
                    pdfInfo = selectedPdfInfo
                )
            }
        }
        SearchInput(
            value = pdfName,
            onValueChange = { newValue ->
                pdfName = newValue
                if (pdfName.isEmpty()) {
                    pdfsListViewModel.getAllPdfs()
                    return@SearchInput
                }
                pdfsListViewModel.filterPdfsByName(pdfName)
            }
        )
        Spacer(Modifier.size(24.dp))
        LazyColumn(
            contentPadding = PaddingValues(10.dp)
        ) {
            items(pdfs) { pdfInfo ->
                PdfInfoItem(
                    pdfInfo = pdfInfo,
                    onDeleteItem = {
                        pdfsListViewModel.showDeleteAlert(pdfInfo)
                    },
                    onSelectItem = {
                        onNavigateToPdfInfoScreen(pdfInfo.id)
                    }
                )
                Spacer(Modifier.size(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PdfsListScreenPreview() {
    EditorDePDFTheme {
        Scaffold { paddingValues ->
            PdfsListScreen(
                paddingValues = paddingValues,
                onNavigateToPdfInfoScreen = {}
            )
        }
    }
}