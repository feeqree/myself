package com.example.myselfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        "signup" -> SignupScreen(onSignupDone = { currentScreen = "signupSuccess" })
        "signupSuccess" -> SignupSuccessScreen(onLoginClick = { currentScreen = "login" })
        "main" -> MainScreen()
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit, onSignupClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2FF59))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.smiley),
            contentDescription = "MySelf Logo",
            modifier = Modifier.size(120.dp)
        )
        Text("MySelf", fontWeight = FontWeight.Bold, fontSize = 32.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Username / Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onSignupClick) {
            Text("Don't have an account? Sign up", color = Color(0xFF6200EA))
        }
    }
}

@Composable
fun SignupScreen(onSignupDone: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2FF59))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SignUp", fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onSignupDone,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
            ) {
                Text("Register", color = Color.White)
            }

            OutlinedButton(onClick = {}) {
                Text("Reset")
            }
        }
    }
}

@Composable
fun SignupSuccessScreen(onLoginClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2FF59)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up Completed!", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text("Please log in with your new account")

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onLoginClick) {
            Text("Login")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2FF59))
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("MySelf") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(id = R.drawable.profile), contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome to the Home Page", fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    MySelfAppTheme {
        AppContent()
    }
}
