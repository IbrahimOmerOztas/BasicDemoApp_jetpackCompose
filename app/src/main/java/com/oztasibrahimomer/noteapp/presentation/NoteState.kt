package com.oztasibrahimomer.noteapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.oztasibrahimomer.noteapp.data.Note

data class NoteState(
    val notes:List<Note> = emptyList(),
    val title:MutableState<String> = mutableStateOf(""),
    val description:MutableState<String> = mutableStateOf("")

)
