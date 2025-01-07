package com.example.myselfapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.myselfapp.ui.theme.MySelfAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewChartScreen(
    navController: NavController,
    onLogoutClick: () -> Unit
) {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    Scaffold(
        topBar = {
            TopBar(
                onLogoutClick = onLogoutClick,
                onHamburgerClick = { navController.navigate("profile") } // Navigate to ProfileScreen
            )
        },
        bottomBar = {
            BottomBar(selectedIndex = 3) { index ->
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
                    .background(Color(0xFFB2FF59))
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                MonthYearSelector(selectedDate) { newDate ->
                    selectedDate = newDate
                }
                Spacer(modifier = Modifier.height(16.dp))
                CalendarGrid(selectedDate)
            }
        }
    )
}

@Composable
fun MonthYearSelector(selectedDate: Calendar, onDateChange: (Calendar) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            selectedDate.add(Calendar.MONTH, -1)
            onDateChange(selectedDate.clone() as Calendar)
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Previous Month")
        }

        Text(
            text = "${SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(selectedDate.time)}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        IconButton(onClick = {
            selectedDate.add(Calendar.MONTH, 1)
            onDateChange(selectedDate.clone() as Calendar)
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription = "Next Month")
        }
    }
}

@Composable
fun CalendarGrid(selectedDate: Calendar) {
    val daysInMonth = selectedDate.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = selectedDate.clone() as Calendar
    firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1)
    val startDayOffset = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1 // Adjust to 0 index

    val daysList = List(startDayOffset) { "" } + (1..daysInMonth).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(daysList) { day ->
            DayItem(day = day) {
                // Handle day selection
            }
        }
    }
}

@Composable
fun DayItem(day: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
            .padding(8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = day, color = if (day.isEmpty()) Color.Transparent else Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun ViewChartScreenPreview() {
    MySelfAppTheme {
        ViewChartScreen(navController = rememberNavController(), onLogoutClick = {})
    }
}
