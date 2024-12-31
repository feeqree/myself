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
import com.example.myselfapp.R
import com.example.myselfapp.viewmodel.Note
import com.example.myselfapp.viewmodel.NotesViewModel
import com.example.myselfapp.ui.theme.MySelfAppTheme
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogoutClick: () -> Unit,
    onFabClick: () -> Unit,
    notesViewModel: NotesViewModel
) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val notesList = notesViewModel.notes

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MySelf") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Hamburger Menu */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(painter = painterResource(id = R.drawable.profile), contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(selectedIndex.intValue) { index -> selectedIndex.intValue = index }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Create, contentDescription = "Add Note")
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
                    text = "Welcome to MySelf",
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

@Composable
fun NoteItem(note: Note) {
    val formattedDate = remember(note.date) {
        java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(note.date)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(text = formattedDate, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = note.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = note.text, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        if (note.mediaUri != null) {
            Text(text = "Media attached", color = Color.Gray)
        }
    }
}



@Composable
fun BottomBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 5.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.quotes),
                    contentDescription = "Quotes"
                ) },

            label = { Text(text = "Quotes") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.moods),
                    contentDescription = "Mood Tracker"
                ) },
            label = { Text(text = "Mood Tracker") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.chart),
                    contentDescription = "View Chart"
                ) },
            label = { Text(text = "View Chart") },
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val notesViewModel = NotesViewModel()

    notesViewModel.addNote(
        Note(
            title = "Sample Title 1",
            text = "This is the content of the first sample note.",
            date = System.currentTimeMillis(),
            mediaUri = null
        )
    )
    notesViewModel.addNote(
        Note(
            title = "Sample Title 2",
            text = "Another example of a note with different content.",
            date = System.currentTimeMillis(),
            mediaUri = null
        )
    )

    MySelfAppTheme {
        MainScreen(
            onLogoutClick = {},
            onFabClick = {},
            notesViewModel = notesViewModel
        )
    }
}
