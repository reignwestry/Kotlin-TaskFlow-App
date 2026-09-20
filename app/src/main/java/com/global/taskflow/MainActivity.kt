package com.global.taskflow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskInputScreen()
                }
            }
        }
    }
}

/**
 * TaskInput Screen combines text description inputs and interactive category selection cards
 * into a unified, state-driven data entry interface.
 */
@Composable
fun TaskInputScreen() {
    val context = LocalContext.current

    //1. Core State Hooks tracking input fields and active category references
    var taskTitle by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Enginneering") }
    var categoriesList = listOf("Engineering", "Database", "Design")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        Text(
            text = "New Task Record",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        //2. Main Title Text Input Field Component
        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Task Description Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Assign Category Tag",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        //3. Dynamic Horizontal Category Selector Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            categoriesList.forEach { category ->
                val isSelected = selectedCategory == category
                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else
                                MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            selectedCategory = category
                        } //Shifting active category state pointer
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.dp.value.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 4.Form Submission and Field Verification Trigger Button
        Button(
            onClick = {
                if (taskTitle.trim().isEmpty()) {
                    Toast.makeText(
                        context,
                        "Validation Error: Title is required!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Saved Successfully: $taskTitle [$selectedCategory]",
                        Toast.LENGTH_LONG
                    ).show()
                    taskTitle = "" //Resetting input fields cleanly upon successful completion
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Confirm and Save Task", fontSize = 16.sp)
        }
    }
}

@Composable
fun TaskFlowTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}