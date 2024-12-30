package com.example.myselfapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.myselfapp.ui.theme.MySelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onLogoutClick: () -> Unit, onFabClick: () -> Unit) {
    val selectedIndex = remember { mutableIntStateOf(0) } // For BottomBar selection

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MySelf") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Hamburger Menu TO-DO
                    }) {
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
            BottomBar(selectedIndex.intValue) { index ->
                selectedIndex.intValue = index
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Create, contentDescription = "Edit")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB2FF59))
                    .padding(innerPadding) // Apply innerPadding to avoid overlap with the BottomBar
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Welcome to the Home Page",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
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
    MySelfAppTheme {
        MainScreen(
            onLogoutClick = {},
            onFabClick = {}
        )
    }
}