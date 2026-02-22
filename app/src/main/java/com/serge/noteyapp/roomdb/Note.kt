package com.serge.noteyapp.roomdb

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("note_title")
    val title: String,
    @ColumnInfo("note_description")
    val description: String,
    val color: Color
)