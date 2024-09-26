package com.oztasibrahimomer.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oztasibrahimomer.noteapp.data.Note
import com.oztasibrahimomer.noteapp.data.NoteDatabase
import com.oztasibrahimomer.noteapp.presentation.NotesEvent
import com.oztasibrahimomer.noteapp.presentation.NotesViewModel
import com.oztasibrahimomer.noteapp.presentation.view.NoteAddScreen
import com.oztasibrahimomer.noteapp.presentation.view.NotesScreen
import com.oztasibrahimomer.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    private val viewModel by  viewModels<NotesViewModel> (
        factoryProducer = {
            object :ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context= LocalContext.current
                    val state=viewModel.state.collectAsState()
                    val navController = rememberNavController()


                    val deneme = viewModel::onEvent



                    NavHost(navController = navController, startDestination = "notesScreen"){
                        composable("notesScreen"){

                            NotesScreen(
                                state=state.value,
                                navController=navController,
                                onEvent = viewModel::onEvent
                            )

                        }
                        composable("noteAddScreen"){
                            NoteAddScreen(
                                state=state.value,
                                navController=navController,
                                onEvent = viewModel::onEvent,
                                context=context
                            )

                        }
                    }







                }
            }
        }
    }
}



