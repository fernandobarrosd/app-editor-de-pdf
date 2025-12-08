package com.fernando.editordepdf.room.converters

import androidx.room.TypeConverter
import com.fernando.editordepdf.enums.PdfState

class PdfStateConverters {
    @TypeConverter
    fun convertToString(pdfState: PdfState) : String {
        return pdfState.name
    }


    @TypeConverter
    fun convertToPdfState(pdfStateName: String) : PdfState {
        return PdfState.entries.find { pdfState -> pdfState.name == pdfStateName }!!
    }
}