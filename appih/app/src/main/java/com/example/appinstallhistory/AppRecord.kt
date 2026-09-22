package com.example.appinstallhistory

data class AppRecord(
    val packageName: String,
    val appName: String,
    val installTime: Long,
    val updateTime: Long,
    val installer: String?
)
