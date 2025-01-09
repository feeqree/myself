package com.example.myselfapp.screens.sign_up

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myselfapp.R.string as AppText
import com.example.myselfapp.common.ext.basicButton
import com.example.myselfapp.common.ext.fieldModifier
import com.example.myselfapp.ui.theme.MySelfAppTheme

@Composable
fun SignupScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    SignUpScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) },
        onBackClick = { /* Handle back navigation */ }
    )
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2FF59))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", fontWeight = FontWeight.Bold, fontSize = 36.sp)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.repeatPassword,
            onValueChange = onRepeatPasswordChange,
            label = { Text("Repeat Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onSignUpClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA))
            ) {
                Text("Register", color = Color.White)
            }

            OutlinedButton(onClick = { /* Handle reset logic */ }) {
                Text("Reset")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val uiState = SignUpUiState(
        email = "email@test.com",
        password = "password",
        repeatPassword = "password"
    )

    MySelfAppTheme {
        SignUpScreenContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onRepeatPasswordChange = { },
            onSignUpClick = { },
            onBackClick = { }
        )
    }
}