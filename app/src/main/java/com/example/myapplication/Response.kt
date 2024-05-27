package com.example.myapplication

data class DataRequest(
    val contents: Contents
)

data class Contents(
    val username: String,
    val password: String,
    val imei: String,
    val subscriberId: String,
    val appVersion: String,
    val deviceName: String
)