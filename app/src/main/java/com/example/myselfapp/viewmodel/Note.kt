package com.example.myselfapp.viewmodel

import android.net.Uri

data class Note(
    val title: String,
    val text: String,
    val mediaUri: Uri? = null,
    val date: Long
)
