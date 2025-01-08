package com.example.myselfapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myselfapp.R
import com.example.myselfapp.navigation.TopBar
import com.example.myselfapp.ui.theme.MySelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
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

    // Map of badge images
    val badgeImages = mapOf(
        "Apprentice" to R.drawable.badge_apprentice,
        "Going Strong" to R.drawable.badge_going_strong,
        "For Ages" to R.drawable.badge_for_ages,
        "Millennium" to R.drawable.badge_millennium,
        "Beyond" to R.drawable.badge_beyond,
        "Mega Streak" to R.drawable.badge_mega_streak,
        "Complex Person" to R.drawable.badge_complex_person,
        "Busy Bee" to R.drawable.badge_busy_bee,
        "Organized" to R.drawable.badge_organized
    )

    // List of earned and locked badges
    val earnedBadges = listOf("Apprentice")
    val lockedBadges = badgeDescriptions.keys.toList().filter { it !in earnedBadges }

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

                        // Earned Badges Grid
                        Text(
                            text = "Earned Badges",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(earnedBadges) { badge ->
                                BadgeItem(badge = badge, onClick = { selectedBadge = badge }, badgeImages)
                            }
                        }

                        // Locked Badges Grid
                        Text(
                            text = "Locked Badges",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(lockedBadges) { badge ->
                                LockedBadgeItem(badge = badge, badgeImages)
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

@Composable
fun BadgeItem(badge: String, onClick: () -> Unit, badgeImages: Map<String, Int>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = badgeImages[badge] ?: R.drawable.badge_default),
                contentDescription = badge,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(4.dp)) // Reduced height for better spacing
            Text(
                text = badge,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp, // Smaller font size
                maxLines = 1, // Limit to one line
                overflow = TextOverflow.Ellipsis // Add ellipsis for overflow
            )
        }
    }
}

@Composable
fun LockedBadgeItem(badge: String, badgeImages: Map<String, Int>) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .alpha(0.5f), // Fade the locked badges
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = badgeImages[badge] ?: R.drawable.badge_default),
                contentDescription = badge,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(4.dp)) // Reduced height for better spacing
            Text(
                text = badge,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp, // Smaller font size
                maxLines = 1, // Limit to one line
                overflow = TextOverflow.Ellipsis // Add ellipsis for overflow
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BadgesScreenPreview() {
    BadgesScreen(navController = rememberNavController())
}
