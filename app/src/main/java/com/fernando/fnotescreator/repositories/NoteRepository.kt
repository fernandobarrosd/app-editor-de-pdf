package com.fernando.fnotescreator.repositories

import com.fernando.fnotescreator.mappers.toEntity
import com.fernando.fnotescreator.mappers.toRoomEntity
import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.room.daos.NoteDAO
import jakarta.inject.Inject

class NoteRepository @Inject constructor(private val noteDAO: NoteDAO) {
    suspend fun saveNote(note: Note) : Note {
        noteDAO.save(note.toRoomEntity())
        return note
    }

    suspend fun findAllNotes() : List<Note> {
        return noteDAO.findAll()
            .map { noteEntity -> noteEntity.toEntity() }
    }

    suspend fun findNoteById(noteID: String) : Note? {
        return noteDAO.findById(noteID)?.toEntity()
    }

    suspend fun deleteNoteById(noteID: String) {
        noteDAO.deleteById(noteID)
    }
}