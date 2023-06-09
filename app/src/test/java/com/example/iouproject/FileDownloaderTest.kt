package com.example.iouproject

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.iouproject.downloader.FileDownloader
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class FileDownloaderTest {

    private lateinit var context: Context
    private lateinit var downloadManager: DownloadManager
    private lateinit var fileDownloader: FileDownloader

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        downloadManager = mock(DownloadManager::class.java)
        fileDownloader = FileDownloader(context)
    }

    @Test
    fun downloadFile_enqueuesDownloadRequest() {
        val url = "https://example.com/file.mp4"

        val fileName = "file.mp4"
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("video/mp4")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        `when`(downloadManager.enqueue(request)).thenReturn(123456L)

        val result = fileDownloader.downloadFile(url)

        verify(downloadManager).enqueue(request)
        assertEquals(123456L, result)
    }

    @Test
    fun parseMimeType_returnsCorrectMimeType() {
        val url = "https://example.com/file.mp4"
        val expectedMimeType = "video/mp4"

        val result = fileDownloader.parseMimeType(url)

        assertEquals(expectedMimeType, result)
    }

    @Test
    fun getFileNameFromUri_returnsCorrectFileName() {
        val url = "https://example.com/file.mp4"
        val expectedFileName = "file.mp4"

        val result = fileDownloader.getFileNameFromUri(url)

        assertEquals(expectedFileName, result)
    }
}