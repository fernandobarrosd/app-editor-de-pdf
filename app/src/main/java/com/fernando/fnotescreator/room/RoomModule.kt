package com.fernando.fnotescreator.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fernando.fnotescreator.BuildConfig
import com.fernando.fnotescreator.room.daos.NoteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        val isDev = BuildConfig.IS_DEV

        val databaseBuilder = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        )

        if (isDev) {
            databaseBuilder.addCallback(object: RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL("""
                        INSERT INTO notes (id, content, name, created_at, is_read_only) VALUES
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000001', 'Conteúdo da Nota 1',  'Nota 1',  '2024-01-10', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000002', 'Conteúdo da Nota 2',  'Nota 2',  '2024-01-11', 1),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000003', 'Conteúdo da Nota 3',  'Nota 3',  '2024-01-12', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000004', 'Conteúdo da Nota 4',  'Nota 4',  '2024-01-13', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000005', 'Conteúdo da Nota 5',  'Nota 5',  '2024-01-14', 1),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000006', 'Conteúdo da Nota 6',  'Nota 6',  '2024-01-15', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000007', 'Conteúdo da Nota 7',  'Nota 7',  '2024-01-16', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000008', 'Conteúdo da Nota 8',  'Nota 8',  '2024-01-17', 1),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000009', 'Conteúdo da Nota 9',  'Nota 9',  '2024-01-18', 0),
                        ('6a1f9c2e-3b4d-4e5f-9a1c-000000000010', 'Conteúdo da Nota 10', 'Nota 10', '2024-01-19', 0);
                    """.trimIndent())
                }
            })
        }

        return databaseBuilder.build()
    }

    @Provides
    @Singleton
    fun noteDAO(appDatabase: AppDatabase) : NoteDAO {
        return appDatabase.noteDAO()
    }
}