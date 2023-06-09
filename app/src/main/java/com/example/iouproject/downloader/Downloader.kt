package com.example.iouproject.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}