package com.fernando.fnotescreator.room.converters

import androidx.room.TypeConverter
import java.time.LocalDate


class LocalDateConverters {
    @TypeConverter
    fun convertToString(localDate: LocalDate) : String {
        return localDate.toString()
    }

    @TypeConverter
    fun convertToLocalDate(localDateString: String) : LocalDate {
        return LocalDate.parse(localDateString)
    }
}