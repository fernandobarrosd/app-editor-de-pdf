package com.fernando.editordepdf.repositories

import com.fernando.editordepdf.mappers.toEntity
import com.fernando.editordepdf.mappers.toRoomEntity
import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.room.daos.PdfInfoDAO
import jakarta.inject.Inject

class PdfInfoRepository @Inject constructor(private val pdfInfoDAO: PdfInfoDAO) {
    suspend fun savePdfInfo(pdfInfo: PdfInfo) : PdfInfo {
        pdfInfoDAO.save(pdfInfo.toRoomEntity())
        return pdfInfo
    }

    suspend fun findAll() : List<PdfInfo> {
        return pdfInfoDAO.findAll()
            .map { pdfInfoEntity -> pdfInfoEntity.toEntity() }
    }

    fun findById(pdfInfoId: String) : PdfInfo? {
        return pdfInfoDAO.findById(pdfInfoId)?.toEntity()
    }

    fun updateToSaved(pdfInfoId: String) : PdfInfo? {
        val pdfInfo = findById(pdfInfoId)
        pdfInfo?.updateToSaved()

        pdfInfoDAO.updateToSaved(pdfInfoId)
        return pdfInfo
    }

    suspend fun deletePdfInfoById(pdfInfoId: String) {
        pdfInfoDAO.deleteById(pdfInfoId)
    }
}