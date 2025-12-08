package com.fernando.editordepdf.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fernando.editordepdf.room.entities.PdfInfoEntity

@Dao
interface PdfInfoDAO {
    @Insert
    suspend fun save(pdfInfoEntity: PdfInfoEntity)

    @Query("SELECT * FROM pdf_infos WHERE id = :pdfInfoID")
    fun findById(pdfInfoID: String) : PdfInfoEntity?

    @Query("SELECT * FROM pdf_infos")
    fun findAll() : List<PdfInfoEntity>

    @Query("DELETE FROM pdf_infos WHERE id = :pdfInfoID")
    suspend fun deleteById(pdfInfoID: String)

    @Query("UPDATE pdf_infos SET state = \"SAVED\" WHERE id = :pdfInfoID")
    fun updateToSaved(pdfInfoID: String)
}