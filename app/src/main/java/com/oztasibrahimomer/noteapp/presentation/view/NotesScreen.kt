package com.oztasibrahimomer.noteapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.oztasibrahimomer.noteapp.R
import com.oztasibrahimomer.noteapp.data.Note
import com.oztasibrahimomer.noteapp.presentation.NoteState
import com.oztasibrahimomer.noteapp.presentation.NotesEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    state:NoteState,
    navController: NavController,
    onEvent: (NotesEvent) ->Unit
) {
    
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier=Modifier
                        .weight(1f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                IconButton(onClick = {
                    onEvent(NotesEvent.SortNotes)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Sort,
                        contentDescription = "Sorting process",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(35.dp)
                    )
                }



            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    state.title.value=""
                    state.description.value=""
                    navController.navigate("noteAddScreen")
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {

                Icon(imageVector = Icons.Default.Add, contentDescription = "Adding button")

            }
        }
    ){paddingValues->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            items(state.notes.size){index->

                NoteItem(note = state.notes[index], onEvent = onEvent)




            }
        }




        
    }

}

@Composable
fun NoteItem(
    note: Note,
    onEvent: (NotesEvent) ->Unit
) {

    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clip(RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Column(
            modifier= Modifier
                .padding(10.dp)
                .width(250.dp)
                .clickable { },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){

            Text(text = note.title, fontSize = 22.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = note.description, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))



        }

        IconButton(onClick = {
            onEvent(NotesEvent.DeleteNote(note))
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete Note")

        }

    }
}

