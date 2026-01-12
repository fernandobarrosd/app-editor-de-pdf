package com.fernando.fnotescreator.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,

    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate
)
