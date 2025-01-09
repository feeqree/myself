package com.example.myselfapp.screens.login

import com.example.myselfapp.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myselfapp.ui.theme.MySelfAppTheme

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = { viewModel.onSignInClick(openAndPopUp) },
        onSignupClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = { viewModel::onForgotPasswordClick }
    )
}


@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
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

        // Email Field
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text("Username / Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password Button
        TextButton(onClick = onForgotPasswordClick) {
            Text("Forgot Password?", color = Color(0xFF6200EA))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        TextButton(onClick = onSignupClick) {
            Text("Don't have an account? Sign up", color = Color(0xFF6200EA))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val uiState = LoginUiState(
        email = "email@test.com"
    )

    MySelfAppTheme {
        LoginScreenContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onLoginClick = TODO(),
            onSignupClick = TODO(),
            onForgotPasswordClick = TODO()
        )
    }
}