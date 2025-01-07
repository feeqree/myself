package com.example.myselfapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.myselfapp.navigation.BottomBar
import com.example.myselfapp.ui.theme.MySelfAppTheme

@Composable
fun BadgesScreen(navController: NavController) {
    var selectedBadge by remember { mutableStateOf<String?>(null) }

    val badgeDescriptions = mapOf(
        "Apprentice" to "You have started your journey towards better mental health!",
        "Going Strong" to "You have used this app for 7 days in a row!",
        "For Ages" to "You have been a loyal user for over a month!",
        "Millennium" to "You have achieved 1000 points in activities!",
        "Beyond" to "You reached beyond your limits today! Keep it up!",
        "Mega Streak" to "You have maintained a streak for 30 days!",
        "Complex Person" to "You completed a self-assessment and learned more about yourself.",
        "Busy Bee" to "You logged multiple activities in a single day!",
        "Organized" to "You planned and achieved your daily mental health goals!"
    )

    MySelfAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDFFFD9)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        onLogoutClick = { /* Handle logout */ },
                        onHamburgerClick = { navController.navigate("profile") } // Navigate to ProfileScreen
                    )
                },
                bottomBar = {
                    BottomBar(selectedIndex = 0) { index ->
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
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Your Badges",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Scrollable Badges List
                        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                            items(badgeDescriptions.keys.toList()) { badge ->
                                Text(
                                    text = badge,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedBadge = badge }
                                        .padding(vertical = 12.dp)
                                        .background(Color.White, shape = MaterialTheme.shapes.medium)
                                        .padding(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Back Button
                        Button(
                            onClick = { navController.popBackStack() }, // Navigate back to ProfileScreen
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Back", color = Color.White)
                        }
                    }
                }
            )
        }

        // Show Badge Description Dialog
        selectedBadge?.let { badge ->
            AlertDialog(
                onDismissRequest = { selectedBadge = null },
                title = { Text(text = badge) },
                text = { Text(text = badgeDescriptions[badge] ?: "No description available.") },
                confirmButton = {
                    TextButton(onClick = { selectedBadge = null }) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BadgesScreenPreview() {
    BadgesScreen(navController = rememberNavController())
}
