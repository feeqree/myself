package com.example.myselfapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myselfapp.screens.*
import com.example.myselfapp.viewmodel.NotesViewModel

@Composable
fun AppNavigation(navController: NavHostController, notesViewModel: NotesViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("main") },
                onSignupClick = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignupScreen(
                onSignupDone = { navController.navigate("signupSuccess") },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("signupSuccess") {
            SignupSuccessScreen(
                onLoginClick = { navController.navigate("login") },
                onBackClick = { navController.navigate("signup") }
            )
        }
        composable("main") {
            MainScreen(
                onLogoutClick = { navController.navigate("login") },
                onFabClick = { navController.navigate("addNote") },
                notesViewModel = notesViewModel
            )
        }
        composable("addNote") {
            AddNoteScreen(
                navController = navController, // Pass NavController here
                onSubmitNote = { note ->
                    notesViewModel.addNote(note)
                    navController.navigate("main") { popUpTo("main") { inclusive = true } }
                },
                onBackClick = { navController.popBackStack() } // Handle back navigation
            )
        }
    }
}
