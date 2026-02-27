package com.serge.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.serge.noteyapp.repository.NotesRepository
import com.serge.noteyapp.roomdb.Note
import com.serge.noteyapp.roomdb.NotesDB
import com.serge.noteyapp.screens.DisplayNoteList
import com.serge.noteyapp.ui.theme.NoteyAppTheme
import com.serge.noteyapp.viewmodel.NoteViewModel
import com.serge.noteyapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //ROOM DB
        val database = NotesDB.getInstance(applicationContext)

        //Repository
        val repository = NotesRepository(database.notesDao)

        //ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(repository)

        //ViewModelProvider
        val noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        val note1 = Note(
            title = "serge",
            description = "Kabir Pancholi who is famously known by the name of serge is an android dev",
            color = "#26408B".toColorInt()
        )

        //inserting the note
        noteViewModel.insert(note1)

        setContent {
            NoteyAppTheme {


                //Display all records
                val notes by noteViewModel
                    .allNotes.observeAsState(emptyList())

                DisplayNoteList(notes = notes)
            }
        }
    }
}