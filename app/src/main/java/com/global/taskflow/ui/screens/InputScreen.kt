package com.global.taskflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * InputScreen captures user input description arguments and hands them directly over to our
 * higher-level architecture layers for processing.
 *
 * InputScreen captures fresh task descriptions from the user.
 *
 * @param onNavigateBack An event lambda that signals the architecture to return to the home screen.
 */
@Composable
fun InputScreen(
    onSaveTask: (String) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
  var textInputState by remember { mutableStateOf("") }

  Column(modifier = modifier.fillMaxSize().background(Color(0xFFF9FAFB)).padding(24.dp)) {
    Text(
        text = "Create New Task",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF111827),
    )

    Spacer(modifier = Modifier.height(24.dp))

    OutlinedTextField(
        value = textInputState,
        onValueChange = { textInputState = it },
        label = { Text("Task Description") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
    )

    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = {
          if (textInputState.isNotBlank()) {
            // Bubble the typed input string up to our navigation coordinator
            onSaveTask(textInputState)
          }
        }, // Navigating back upon submission event
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
    ) {
      Text("Confirm and Save Task", fontSize = 16.sp)
    }
  }
}
