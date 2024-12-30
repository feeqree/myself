package com.example.myselfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myselfapp.screens.*
import com.example.myselfapp.ui.theme.MySelfAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySelfAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(
            onLoginClick = { currentScreen = "main" },
            onSignupClick = { currentScreen = "signup" }
        )
        "signup" -> SignupScreen(
            onSignupDone = { currentScreen = "signupSuccess" },
            onBackClick = { currentScreen = "login" }
        )
        "signupSuccess" -> SignupSuccessScreen(
            onLoginClick = { currentScreen = "login" },
            onBackClick = { currentScreen = "signup" }
        )
        "main" -> MainScreen(onLogoutClick = { currentScreen = "login" }, onFabClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    MySelfAppTheme {
        AppContent()
    }
}

