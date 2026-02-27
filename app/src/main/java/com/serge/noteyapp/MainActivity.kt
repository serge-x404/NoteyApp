package com.serge.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.serge.noteyapp.repository.NotesRepository
import com.serge.noteyapp.roomdb.Note
import com.serge.noteyapp.roomdb.NotesDB
import com.serge.noteyapp.screens.DisplayDialog
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

                Scaffold(
                    floatingActionButton = { FAB(viewModel = noteViewModel)}
                ) { Modifier.padding(it)
                    //Display all records
                    val notes by noteViewModel
                        .allNotes.observeAsState(emptyList())

                    DisplayNoteList(notes = notes)
                }
            }
        }
    }
}


@Composable
fun FAB(viewModel: NoteViewModel) {

    var showDialog by remember { mutableStateOf(false) }

    DisplayDialog(
        viewModel = viewModel,
        showDialog = showDialog
    ) {
        showDialog = false
    }

    FloatingActionButton(
        onClick = { showDialog = true},
        containerColor = Color.Gray,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null
        )
    }
}