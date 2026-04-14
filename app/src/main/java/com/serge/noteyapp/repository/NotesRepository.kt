package com.serge.noteyapp.repository

import androidx.lifecycle.LiveData
import com.serge.noteyapp.roomdb.Note
import com.serge.noteyapp.roomdb.NoteDao

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note){
        return noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }
}