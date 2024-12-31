package com.example.myselfapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myselfapp.viewmodel.Note

class NotesViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }
}
