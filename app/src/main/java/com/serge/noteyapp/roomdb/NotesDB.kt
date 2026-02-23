package com.serge.noteyapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB: RoomDatabase() {
    abstract val notesDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getInstance(context: Context): NotesDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDB::class.java,
                        "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}