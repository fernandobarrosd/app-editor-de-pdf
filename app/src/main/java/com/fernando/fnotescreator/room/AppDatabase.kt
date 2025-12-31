package com.fernando.fnotescreator.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fernando.fnotescreator.room.converters.LocalDateConverters
import com.fernando.fnotescreator.room.daos.NoteDAO
import com.fernando.fnotescreator.room.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(LocalDateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDAO() : NoteDAO
}