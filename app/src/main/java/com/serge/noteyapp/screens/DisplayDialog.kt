package com.serge.noteyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.serge.noteyapp.roomdb.Note
import com.serge.noteyapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayDialog(
    viewModel: NoteViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Enter Note") },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it},
                        label = { Text("Note Title") }
                    )
                    Spacer(Modifier.height(12.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it},
                        label = { Text("Note Description") }
                    )
                    Spacer(Modifier.height(12.dp))

                    //Color Picker Composable


                }
            },

            confirmButton = {
                Button(onClick = {
                    val note = Note(
                        0,
                        title,
                        description,
                        selectedColor.toArgb()
                    )

                    // Inserting into DB
                    viewModel.insert(note)
                }) {
                    Text("Save Note")
                }
            },

            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}