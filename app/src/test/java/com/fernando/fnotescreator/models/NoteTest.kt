package com.fernando.fnotescreator.models

import org.junit.Test
import java.time.LocalDate
import org.junit.Assert.*
import java.util.UUID

class PdfInfoTest {
    @Test
    fun shouldCreateNoteWithoutId() {
        val note = Note(
            content = "Conteudo teste",
            isReadOnly = true,
            name = "Note test"
        )

        assertEquals(note.content, "Conteudo teste")
        assertEquals(note.isReadOnly, true)
        assertEquals(note.name, "Note test")
    }

    @Test
    fun shouldCreateNoteWithId() {
        val createdAt = LocalDate.now()
        val pdfInfoID = UUID.randomUUID().toString()

        val note = Note(
            id = pdfInfoID,
            content = "Conteudo teste",
            createdAt = createdAt,
            isReadOnly = true,
            name = "Note test"
        )

        assertEquals(note.id, pdfInfoID)
        assertEquals(note.content, "Conteudo teste")
        assertEquals(note.createdAt, createdAt)
        assertEquals(note.isReadOnly, true)
        assertEquals(note.name, "Note test")
    }
}