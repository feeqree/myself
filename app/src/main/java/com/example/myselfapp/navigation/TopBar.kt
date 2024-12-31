package com.example.myselfapp.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myselfapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onLogoutClick: () -> Unit,
    onHamburgerClick: () -> Unit
) {
    TopAppBar(
        title = { Text("MySelf") },
        navigationIcon = {
            IconButton(onClick = onHamburgerClick) {
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
}
