package com.serge.noteyapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.serge.noteyapp.roomdb.Note
import com.serge.noteyapp.viewmodel.NoteViewModel
import com.serge.noteyapp.viewmodel.NoteViewModelFactory

@Composable
fun NoteListItem(note: Note, noteViewModel: NoteViewModel){
    var showDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }
    var selectedColor by remember { mutableStateOf(Color(note.color)) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
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
                    ColorPicker(
                        selectedColor = selectedColor,
                        onColorSelected = { selectedColor = it}
                    )
                }
            },

            confirmButton = {
                Button(onClick = {
                    noteViewModel.update(
                        note.copy(
                            title = title,
                            description = description,
                            color = selectedColor.toArgb()
                        )
                    )
                    showDialog = false
                }) {
                    Text("Update Note")
                }
            },

            dismissButton = {
                Button(onClick = {showDialog = false}) {
                    Text("Cancel")
                }
            }
        )
    }

    Card(
        onClick ={ showDialog = true },
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(Color(note.color)),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(4.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ) {
            Text(
                text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = note.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}