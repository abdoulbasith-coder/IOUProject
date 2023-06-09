package com.example.iouproject.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import java.io.File

class FileDownloader (context: Context) : Downloader {

    private val downloadManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.getSystemService(DownloadManager::class.java)
    } else {
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    override fun downloadFile(url: String): Long {
        val fileName= getFileNameFromUri(url);
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(parseMimeType(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        Log.d("Download", "filepath : $fileName")
        return downloadManager.enqueue(request)
    }

    private fun parseMimeType(url: String): String {
        val file = File(url)
        val map = MimeTypeMap.getSingleton()
        val ext = MimeTypeMap.getFileExtensionFromUrl(file.name)
        var type = map.getMimeTypeFromExtension(ext)
        type = type ?: "*/*"
        return type;
    }

    private fun getFileNameFromUri(url: String): String {
        return url.substring( url.lastIndexOf('/')+1, url.length);
    }
}