package com.fernando.fnotescreator.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fernando.fnotescreator.room.entities.NoteEntity

@Dao
interface NoteDAO {
    @Insert
    suspend fun save(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :noteID")
    suspend fun findById(noteID: String) : NoteEntity?

    @Query("SELECT * FROM notes")
    suspend fun findAll() : List<NoteEntity>

    @Query("DELETE FROM notes WHERE id = :noteID")
    suspend fun deleteById(noteID: String)

    @Query("UPDATE notes SET content = :newContent WHERE id = :noteID")
    suspend fun updateNoteContent(newContent: String, noteID: String)
}