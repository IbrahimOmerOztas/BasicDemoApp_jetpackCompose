package com.oztasibrahimomer.noteapp.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.oztasibrahimomer.noteapp.R
import com.oztasibrahimomer.noteapp.presentation.NoteState
import com.oztasibrahimomer.noteapp.presentation.NotesEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteAddScreen(

    state:NoteState,
    navController: NavController,
    onEvent:(NotesEvent) -> Unit,
    context:Context
) {

    val titleTf=state.title
    val descriptionTf = state.description


    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Adding Note Screen", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(25.dp))

        TextField(value = titleTf.value, onValueChange = {titleTf.value=it}, label = { Text(text = "Title")})

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = descriptionTf.value, onValueChange = {descriptionTf.value=it}, label = { Text(text = "Description")})

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if(!titleTf.value.equals("") && !descriptionTf.value.equals("")){
                onEvent(NotesEvent.SaveNote(titleTf.value,descriptionTf.value))
                navController.popBackStack()

            }
            else{
                Toast.makeText(context,"Lutfen alanları bos birakma!!!",Toast.LENGTH_SHORT).show()
                titleTf.value=""
                descriptionTf.value=""

            }


        }) {
            Text(text = "Add Note")
        }

    }


}