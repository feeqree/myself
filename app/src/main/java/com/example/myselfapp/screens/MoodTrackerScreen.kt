package com.example.myselfapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myselfapp.navigation.TopBar
import com.example.myselfapp.ui.theme.MySelfAppTheme

@Composable
fun MoodTrackerScreen(
    navController: NavController,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onLogoutClick = onLogoutClick,
                onHamburgerClick = { navController.navigate("profile") } // Navigate to ProfileScreen
            )
        },
        bottomBar = {
            BottomBar(selectedIndex = 2) { index ->
                when (index) {
                    0 -> navController.navigate("main") {
                        popUpTo("main") { inclusive = true }
                        launchSingleTop = true
                    }
                    1 -> navController.navigate("quotes") {
                        popUpTo("quotes") { inclusive = true }
                        launchSingleTop = true
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
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFB2FF59)), // Match MainScreen background color
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Mood Tracker",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Mood Tracking UI
                MoodTrackingUI(
                    onMoodSelected = { mood ->
                        // Handle mood selection
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary), // Match button color
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Back", color = Color.White)
                }
            }
        }
    )
}

@Composable
fun MoodTrackingUI(
    onMoodSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("How have you been feeling right now?")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MoodButton("Happy", onMoodSelected)
            MoodButton("Meh", onMoodSelected)
            MoodButton("Sad", onMoodSelected)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MoodButton("Angry", onMoodSelected)
            MoodButton("Awful", onMoodSelected)
            MoodButton("Write Something...", onMoodSelected)
        }
    }
}

@Composable
fun MoodButton(
    mood: String,
    onMoodSelected: (String) -> Unit
) {
    Button(
        onClick = { onMoodSelected(mood) },
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White) // Keep button color white for mood buttons
    ) {
        Text(mood)
    }
}

@Composable
@Preview
fun MoodTrackerScreenPreview() {
    MySelfAppTheme {
        MoodTrackerScreen(navController = rememberNavController(), onLogoutClick = {})
    }
}