package com.example.myselfapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myselfapp.R
import com.example.myselfapp.navigation.TopBar
import com.example.myselfapp.viewmodel.Note
import com.example.myselfapp.viewmodel.NotesViewModel
import com.example.myselfapp.ui.theme.MySelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreen(
    navController: NavController,
    onLogoutClick: () -> Unit,
    onFabClick: () -> Unit,
    notesViewModel: NotesViewModel
) {
    val selectedIndex = remember { mutableIntStateOf(1) } // Index for the Quotes tab
    val notesList = notesViewModel.notes

    // Default quotes if the user hasn't added any
    if (notesList.isEmpty()) {
        val defaultQuotes = listOf(
            "The only way to do great work is to love what you do.",
            "Believe you can and you're halfway there.",
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
            "The mind is everything. What you think you become.",
            "The best way to predict the future is to create it.",
            "You are capable of amazing things.",
            "Don't be afraid to fail. It's the only way to succeed .",
            "Your limitation—it's only your imagination.",
            "Push yourself, because no one else is going to do it for you.",
            "Great things never come from comfort zones."
        )
        defaultQuotes.forEach { quote ->
            notesViewModel.addNote(
                Note(
                    title = "Motivational Quote",
                    text = quote,
                    date = System.currentTimeMillis(),
                    mediaUri = null
                )
            )
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                onLogoutClick = onLogoutClick,
                onHamburgerClick = { navController.navigate("profile") }
            )
        },
        bottomBar = {
            BottomBar(selectedIndex = 1) { index ->
                when (index) {
                    0 -> navController.navigate("main") {

                    }
                    1 -> navController.navigate("quotes") {

                    }
                    2 -> navController.navigate("mood_tracker") {
                        // Navigate to MoodTrackerScreen
                    }
                    3 -> navController.navigate("view_chart") {
                        // Navigate to ViewChartScreen
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Create, contentDescription = "Add Quote")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB2FF59))
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Quotes",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(notesList) { note ->
                        NoteItem(note = note)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun QuotesScreenPreview() {
    val notesViewModel = NotesViewModel()

    // Adding default motivational quotes for preview
    listOf(
        "The only way to do great work is to love what you do.",
        "Believe you can and you're halfway there.",
        "Success is not final, failure is not fatal: it is the courage to continue that counts.",
        "The mind is everything. What you think you become.",
        "The best way to predict the future is to create it.",
        "You are capable of amazing things.",
        "Don't be afraid to fail. It's the only way to succeed.",
        "Your limitation—it's only your imagination.",
        "Push yourself, because no one else is going to do it for you.",
        "Great things never come from comfort zones."
    ).forEach { quote ->
        notesViewModel.addNote(
            Note(
                title = "Motivational Quote",
                text = quote,
                date = System.currentTimeMillis(),
                mediaUri = null
            )
        )
    }

    MySelfAppTheme {
        QuotesScreen(
            navController = rememberNavController(),
            onLogoutClick = {},
            onFabClick = {},
            notesViewModel = notesViewModel
        )
    }
}