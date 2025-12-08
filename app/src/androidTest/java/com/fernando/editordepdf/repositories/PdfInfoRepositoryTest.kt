package com.fernando.editordepdf.repositories

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fernando.editordepdf.enums.PdfState
import com.fernando.editordepdf.models.PdfInfo
import com.fernando.editordepdf.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.Assert.*
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class PdfInfoRepositoryTest {
    private lateinit var pdfInfoRepository: PdfInfoRepository
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        pdfInfoRepository = PdfInfoRepository(appDatabase.pdfInfoDao())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun shouldSavePdfInfo() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste",
            isReadOnly = true
        )

        CoroutineScope(Dispatchers.IO).launch {
            val pdfInfoSaved = pdfInfoRepository.savePdfInfo(pdfInfo)
            assertEquals(pdfInfoSaved.id, pdfInfo.id)
            assertEquals(pdfInfoSaved.content, pdfInfo.content)
            assertEquals(pdfInfoSaved.createdAt, pdfInfo.createdAt)
            assertEquals(pdfInfoSaved.isReadOnly, pdfInfo.isReadOnly)
            assertEquals(pdfInfoSaved.state, pdfInfo.state)
        }
    }

    @Test
    fun showReturnNotNullPdfInfoWhenCallFindByIdMethod() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste",
            isReadOnly = true
        )

        CoroutineScope(Dispatchers.IO).launch {
            pdfInfoRepository.savePdfInfo(pdfInfo)
            val pdfInfoFind = pdfInfoRepository.findById(pdfInfo.id)

            assertNotNull(pdfInfo)
            assertEquals(pdfInfoFind?.id, pdfInfo.id)
            assertEquals(pdfInfoFind?.content, pdfInfo.content)
            assertEquals(pdfInfoFind?.createdAt, pdfInfo.createdAt)
            assertEquals(pdfInfoFind?.isReadOnly, pdfInfo.isReadOnly)
            assertEquals(pdfInfoFind?.state, pdfInfo.state)
        }
    }

    @Test
    fun showReturnNullPdfInfoWhenCallFindByIdMethod() {
        CoroutineScope(Dispatchers.IO).launch {
            val pdfInfoFind = pdfInfoRepository.findById(UUID.randomUUID().toString())

            assertNull(pdfInfoFind)
        }
    }

    @Test
    fun showReturnPdfInfoListWhenCallFindAllMethod() {
        val pdfs = listOf(
            PdfInfo(
                content = "Conteudo teste 1",
                isReadOnly = true
            ),
            PdfInfo(
                content = "Conteudo teste 3",
                isReadOnly = true
            ),
            PdfInfo(
                content = "Conteudo teste 2",
                isReadOnly = true
            )
        )
        CoroutineScope(Dispatchers.IO).launch {
            pdfs.forEach { pdfInfo ->
                pdfInfoRepository.savePdfInfo(pdfInfo)
            }
            val allPdfs = pdfInfoRepository.findAll()

            assertEquals(allPdfs.size, 3)
            assertEquals(
                allPdfs[0].content,
                "Conteudo teste 1"
            )
            assertEquals(
                allPdfs[1].content,
                "Conteudo teste 2"
            )
            assertEquals(
                allPdfs[2].content,
                "Conteudo teste 3"
            )
        }
    }

    @Test
    fun shouldReturnPdfInfoUpdatedWhenCallUpdateToSavedMethod() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste 1",
            isReadOnly = true
        )

        CoroutineScope(Dispatchers.IO).launch {
            pdfInfoRepository.savePdfInfo(pdfInfo)
            val pdfInfoUpdated = pdfInfoRepository.updateToSaved(pdfInfo.id)

            assertNotNull(pdfInfoUpdated)
            assertEquals(pdfInfoUpdated?.state, PdfState.SAVED)
            assertNotEquals(pdfInfo.state, pdfInfoUpdated?.state)
        }
    }

    @Test
    fun shouldReturnNullIfPdfIsNotExistsWhenCallUpdateToSavedMethod() {
        CoroutineScope(Dispatchers.IO).launch {
            val pdfInfoUpdated = pdfInfoRepository.updateToSaved(UUID.randomUUID().toString())
            assertNull(pdfInfoUpdated)
        }
    }

    @Test
    fun shouldDeletePdfWhenCallDeletePdfInfoByIdMethod() {
        val pdfInfo = PdfInfo(
            content = "Conteudo teste 1",
            isReadOnly = true
        )
        CoroutineScope(Dispatchers.IO).launch {
            pdfInfoRepository.savePdfInfo(pdfInfo)
            pdfInfoRepository.deletePdfInfoById(pdfInfo.id)

            val pdfDeleted = pdfInfoRepository.findById(pdfInfo.id)
            assertNull(pdfDeleted)
        }
    }
}