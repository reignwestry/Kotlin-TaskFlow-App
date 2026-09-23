package com.global.taskflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.global.taskflow.R
import com.global.taskflow.data.model.TaskItem

/**
 * DashboardScreen renders the task repository. It is entirely passive, using a LazyColumn component
 * to loop and draw whatever list data it receives.
 *
 * DashboardScreen displays the primary task list workspace.
 *
 * @param onNavigateToInput An event lambda that signals the navigation graph to transition screens.
 */
@Composable
fun DashboardScreen(
    tasks: List<TaskItem>,
    onNavigateToInput: () -> Unit,
    modifier: Modifier = Modifier,
) {
  Box(modifier = modifier.fillMaxSize().background(Color(0xFFF9FAFB))) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
      Text(
          text = "TaskFlow",
          fontSize = 28.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF111827),
      )

      Spacer(modifier = Modifier.height(20.dp))

      if (tasks.isEmpty()) {
        // Render a clean fallback message if our StateFlow contains zero items
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
          Text(
              text = "No tasks registered yet. Click below to start!",
              color = Color.Gray,
          )
        }
      } else {
        // High-performance scrolling loop container that efficiently draws cards
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp),
        ) {
          items(tasks) { task ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
              Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = task.category,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
              }
            }
          }
        }
      }
    }

    // Floating Action Button invokes our navigation event signal lambda directly on click
    FloatingActionButton(
        onClick = { onNavigateToInput() },
        modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp),
        containerColor = Color(0xFF2563EB),
        contentColor = Color.White,
    ) {
      Icon(
          painter = painterResource(R.drawable.rounded_add_box_24),
          contentDescription = "Navigate to Input Screen",
      )
    }
  }
}
