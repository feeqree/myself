package com.example.myselfapp.screens

import com.example.myselfapp.R

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myselfapp.ui.theme.MySelfAppTheme

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

