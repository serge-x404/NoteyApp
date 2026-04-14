package com.serge.noteyapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    // Define methods for various DB Ops.

    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)
}