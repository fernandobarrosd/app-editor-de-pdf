package com.fernando.editordepdf.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fernando.editordepdf.enums.PdfState
import java.time.LocalDate

@Entity(tableName = "pdf_infos")
data class PdfInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val content: String,

    @ColumnInfo(name = "created_at")
    val createdAt: LocalDate,

    @ColumnInfo(name = "is_read_only")
    val isReadOnly: Boolean,
    val state: PdfState
)
