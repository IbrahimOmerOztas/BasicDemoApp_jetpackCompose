package com.oztasibrahimomer.noteapp.presentation

import com.oztasibrahimomer.noteapp.data.Note

sealed interface NotesEvent {

    data object SortNotes:NotesEvent

    data class DeleteNote(val note:Note):NotesEvent

    data class SaveNote(val title:String, val description:String):NotesEvent


}


