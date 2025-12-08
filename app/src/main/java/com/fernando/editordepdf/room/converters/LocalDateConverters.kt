package com.fernando.editordepdf.room.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate


class LocalDateConverters {
    @TypeConverter
    fun convertToString(localDate: LocalDate) : String {
        return localDate.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun convertToLocalDate(localDateString: String) : LocalDate {
        return LocalDate.parse(localDateString)
    }
}