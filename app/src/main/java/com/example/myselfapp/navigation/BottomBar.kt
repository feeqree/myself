package com.example.myselfapp.navigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myselfapp.R

@Composable
fun BottomBar(navController: NavController, selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 5.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = selectedIndex == 0,
            onClick = {
                onItemSelected(0)

            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.quotes),
                    contentDescription = "Quotes"
                )
            },
            label = { Text(text = "Quotes") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.moods),
                    contentDescription = "Mood Tracker"
                )
            },
            label = { Text(text = "Mood Tracker") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.chart),
                    contentDescription = "View Chart"
                )
            },
            label = { Text(text = "View Chart") },
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) }
        )
    }
}
