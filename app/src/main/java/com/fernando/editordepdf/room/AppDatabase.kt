package com.fernando.editordepdf.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fernando.editordepdf.room.converters.LocalDateConverters
import com.fernando.editordepdf.room.converters.PdfStateConverters
import com.fernando.editordepdf.room.daos.PdfInfoDAO
import com.fernando.editordepdf.room.entities.PdfInfoEntity

@Database(entities = [PdfInfoEntity::class], version = 1)
@TypeConverters(PdfStateConverters::class, LocalDateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pdfInfoDao() : PdfInfoDAO

    /*companion object {
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                ).build()

                INSTANCE = instance
            }
            return instance
        }
    }*/
}