package com.fernando.fnotescreator.repositories

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.room.AppDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class NoteRepositoryTest {
    private lateinit var noteRepository: NoteRepository
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        noteRepository = NoteRepository(appDatabase.noteDAO())
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun shouldSaveNote() {
        runTest {
            val note = Note(
                content = "Conteudo teste",
                name = "Note test"
            )
            val noteSaved = noteRepository.saveNote(note)

            assertEquals(noteSaved.id, note.id)
            assertEquals(noteSaved.content, note.content)
            assertEquals(noteSaved.createdAt, note.createdAt)
        }
    }

    @Test
    fun showReturnNotNullNoteWhenCallFindByIdMethod() {
        runTest {
            val note = Note(
                content = "Conteudo teste",
                name = "Note test"
            )

            noteRepository.saveNote(note)
            val noteFind = noteRepository.findNoteById(note.id)

            assertNotNull(noteFind)
            assertEquals(noteFind?.id, note.id)
            assertEquals(noteFind?.content, note.content)
            assertEquals(noteFind?.createdAt, note.createdAt)
        }
    }

    @Test
    fun showReturnNullNoteWhenCallFindByIdMethod() {
        runTest {
            val noteFind = noteRepository.findNoteById(UUID.randomUUID().toString())

            assertNull(noteFind)
        }
    }

    @Test
    fun showReturnNoteListWhenCallFindAllMethod() {
        runTest {
            val notes = listOf(
                Note(
                    content = "Conteudo teste 1",
                    name = "Note test"
                ),
                Note(
                    content = "Conteudo teste 2",
                    name = "Note test"
                ),
                Note(
                    content = "Conteudo teste 3",
                    name = "Note test"
                )
            )
            notes.forEach { note ->
                noteRepository.saveNote(note)
            }
            val allNotes = noteRepository.findAllNotes()

            assertEquals(allNotes.size, 3)
            assertEquals(
                allNotes[0].content,
                "Conteudo teste 1"
            )
            assertEquals(
                allNotes[1].content,
                "Conteudo teste 2"
            )
            assertEquals(
                allNotes[2].content,
                "Conteudo teste 3"
            )
        }
    }

    @Test
    fun shouldDeleteNoteWhenCallDeleteNoteByIdMethod() {
        runTest {
            val note = Note(
                content = "Conteudo teste 1",
                name = "Note test"
            )
            noteRepository.saveNote(note)
            noteRepository.deleteNoteById(note.id)

            val noteDeleted = noteRepository.findNoteById(note.id)
            assertNull(noteDeleted)
        }
    }
}