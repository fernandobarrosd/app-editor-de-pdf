package com.fernando.editordepdf.models

import com.fernando.editordepdf.enums.PdfState
import org.junit.Test
import java.time.LocalDate
import org.junit.Assert.*
import java.util.UUID

class PdfInfoTest {
    @Test
    fun shouldCreatePdfInfoWithoutId() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste",
            isReadOnly = true,
            name = "Pdf test"
        )

        assertEquals(pdfInfo.content, "Conteudo teste")
        assertEquals(pdfInfo.isReadOnly, true)
        assertEquals(pdfInfo.state, PdfState.NOT_SAVED)
        assertEquals(pdfInfo.name, "Pdf test")
    }

    @Test
    fun shouldCreatePdfInfoWithId() {
        val createdAt = LocalDate.now()
        val pdfInfoID = UUID.randomUUID().toString()

        val pdfInfo = PdfInfo(
            id = pdfInfoID,
            content = "Conteudo teste",
            createdAt = createdAt,
            isReadOnly = true,
            state = PdfState.SAVED,
            name = "Pdf test"
        )

        assertEquals(pdfInfo.id, pdfInfoID)
        assertEquals(pdfInfo.content, "Conteudo teste")
        assertEquals(pdfInfo.createdAt, createdAt)
        assertEquals(pdfInfo.isReadOnly, true)
        assertEquals(pdfInfo.state, PdfState.SAVED)
        assertEquals(pdfInfo.name, "Pdf test")
    }

    @Test
    fun shouldUpdatePdfInfoStateToSaved() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste",
            isReadOnly = true,
            name = "Pdf test"
        )

        pdfInfo.updateToSaved()
        assertEquals(pdfInfo.state, PdfState.SAVED)
    }
}