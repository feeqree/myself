package com.example.myselfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myselfapp.navigation.AppNavigation
import com.example.myselfapp.ui.theme.MySelfAppTheme
import com.example.myselfapp.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySelfAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val notesViewModel: NotesViewModel = viewModel()
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, notesViewModel = notesViewModel)
                }
            }
        }
    }
}
