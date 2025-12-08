package com.fernando.editordepdf.models

import com.fernando.editordepdf.enums.PdfState
import org.junit.Test
import java.time.LocalDate
import org.junit.Assert.*
import java.util.UUID

class PdfInfoTest {
    @Test
    fun shouldCreatePdfInfoWithoutId() {
        val createdAt = LocalDate.now()

        val pdfInfo = PdfInfo(
            content = "Conteudo teste",
            isReadOnly = true
        )

        assertEquals(pdfInfo.content, "Conteudo teste")
        assertEquals(pdfInfo.isReadOnly, true)
        assertEquals(pdfInfo.state, PdfState.NOT_SAVED)
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
        )

        assertEquals(pdfInfo.id, pdfInfoID)
        assertEquals(pdfInfo.content, "Conteudo teste")
        assertEquals(pdfInfo.createdAt, createdAt)
        assertEquals(pdfInfo.isReadOnly, true)
        assertEquals(pdfInfo.state, PdfState.SAVED)
    }

    @Test
    fun shouldUpdatePdfInfoStateToSaved() {
        val createdAt = LocalDate.now()
        val pdfInfoID = UUID.randomUUID().toString()

        val pdfInfo = PdfInfo(
            id = pdfInfoID,
            content = "Conteudo teste",
            createdAt = createdAt,
            isReadOnly = true,
            state = PdfState.NOT_SAVED,
        )

        pdfInfo.updateToSaved()

        assertEquals(pdfInfo.id, pdfInfoID)
        assertEquals(pdfInfo.content, "Conteudo teste")
        assertEquals(pdfInfo.createdAt, createdAt)
        assertEquals(pdfInfo.isReadOnly, true)
        assertEquals(pdfInfo.state, PdfState.SAVED)
    }
}