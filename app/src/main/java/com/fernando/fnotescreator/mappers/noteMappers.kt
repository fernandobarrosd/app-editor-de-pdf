package com.fernando.fnotescreator.mappers

import com.fernando.fnotescreator.models.Note
import com.fernando.fnotescreator.room.entities.NoteEntity
import com.fernando.fnotescreator.ui.screens.addNoteScreen.SaveNoteDTO

fun SaveNoteDTO.toEntity() : Note {
    return Note(name = name, content = content, isReadOnly = isReadOnly)
}

fun Note.toRoomEntity() : NoteEntity {
    return NoteEntity(
        id = id,
        content = content,
        createdAt = createdAt,
        isReadOnly = isReadOnly,
        name = name
    )
}

fun NoteEntity.toEntity() : Note {
    return Note(
        id = id,
        content = content,
        createdAt = createdAt,
        isReadOnly = isReadOnly,
        name = name
    )
}