package com.global.taskflow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
          TaskFlowInputForm()
        }
      }
    }
  }
}

/**
 * TaskFlowInputForm demonstrates how state variables and event listeners manage user input fields
 * and validation events cleanly.
 */
@Composable
fun TaskFlowInputForm() {
  val currentContext = LocalContext.current

  // 1. Instantiating our reactive local state wrapper for the text input
  var taskTitleInput by remember { mutableStateOf("") }

  Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF9FAFB)).padding(24.dp)) {
    Text(
        text = "Create New Task",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF111827),
    )

    Spacer(modifier = Modifier.height(24.dp))

    // 2. OutlinedTextField component mapped directly to our input state and event handler
    OutlinedTextField(
        value = taskTitleInput, // Reading our current text state value
        onValueChange = { freshText -> taskTitleInput = freshText }, // Updating state on keystroke
        label = { Text("Task Description") },
        placeholder = { Text("What needs to be done?") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )

    Spacer(modifier = Modifier.height(16.dp))

    // 3. Submission button that intercepts click events to execute validation rules
    Button(
        onClick = {
          if (taskTitleInput.trim().isEmpty()) {
            Toast.makeText(currentContext, "Error: Description cannot be empty", Toast.LENGTH_SHORT)
                .show()
          } else {
            Toast.makeText(currentContext, "Saved: $taskTitleInput", Toast.LENGTH_SHORT).show()
            taskTitleInput = "" // Clearing our input state after successful processing
          }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
      Text(text = "Save Task Record", fontSize = 16.sp)
    }
  }
}
