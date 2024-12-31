package com.example.myselfapp.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myselfapp.R
import com.example.myselfapp.navigation.TopBar
import com.example.myselfapp.viewmodel.Note
import com.example.myselfapp.ui.theme.MySelfAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navController: NavController,
    onSubmitNote: (Note) -> Unit,
    onBackClick: () -> Unit
) {
    // List of random prompts
    val prompts = listOf(
        stringResource(id = R.string.prompt_feel_today),
        stringResource(id = R.string.prompt_what_happened),
        stringResource(id = R.string.prompt_on_your_mind),
        stringResource(id = R.string.prompt_about_yourself)
    )

    val randomPrompt = remember { prompts.random() }
    var noteTitle by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var mediaUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopBar(
                onLogoutClick = { /* Handle logout */ },
                onHamburgerClick = { /* Handle hamburger menu */ }
            )
        },
        bottomBar = {
            BottomBar(selectedIndex = 0) { index ->
                when (index) {
                    0 -> navController.navigate("main") {
                        popUpTo("main") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFB2FF59))
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.add_note_prompt),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Title input
                BasicTextField(
                    value = noteTitle,
                    onValueChange = { noteTitle = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, MaterialTheme.shapes.medium)
                        .padding(16.dp),
                    decorationBox = { innerTextField ->
                        if (noteTitle.isEmpty()) {
                            Text("Title", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Note input with prompt
                BasicTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, MaterialTheme.shapes.medium)
                        .padding(16.dp),
                    decorationBox = { innerTextField ->
                        if (noteText.isEmpty()) {
                            Text(randomPrompt, color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { /* TODO: Handle Media Selection */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ){
                    Text(stringResource(id = R.string.attach_media))
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSubmitNote(
                            Note(
                                title = noteTitle,
                                text = noteText,
                                mediaUri = mediaUri,
                                date = System.currentTimeMillis()
                            )
                        )
                        navController.navigate("main") {
                            popUpTo("main") { inclusive = true }
                        }
                    },
                    enabled = noteTitle.isNotBlank() && noteText.isNotBlank(),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(id = R.string.submit))
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}
