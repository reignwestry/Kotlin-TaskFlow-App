package com.global.taskflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.global.taskflow.R
import com.global.taskflow.data.model.TaskItem

val drawableIcon =
    /**
     * DashboardScreen displays your persistent database tasks inside an optimized scrolling list.
     * This screen is entirely passive, rendering whatever list data it receives from higher layers.
     */
    @Composable
    fun DashboardScreen(
        tasks: List<TaskItem>,
        onNavigateToInput: () -> Unit,
        onToggleTask: (TaskItem) -> Unit,
        modifier: Modifier = Modifier,
    ) {
      Box(modifier = modifier.fillMaxSize().background(Color(0xFFF9FAFB))) {
        Column(modifier = Modifier.fillMaxSize()) {
          // Centralized Header Title Block Container
          Text(
              text = "TaskFlow Workspace",
              fontSize = 26.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF111827),
              modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 12.dp),
          )

          if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
              Text(
                  text = "No tasks stored yet. Press button to create!",
                  color = Color.Gray,
                  fontSize = 16.sp,
              )
            }
          } else {
            // High-performance virtualized scrolling column layout
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                // ContentPadding adds space around the edges of the list without clipping row
                // scrolls
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
              // Injecting an explicit type-safe key block using our model unique IDs
              items(
                  items = tasks,
                  key = { taskItem -> taskItem.id }, // Structural stable identifier
              ) { task ->
                Card(
                    modifier =
                        Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).clickable {
                          onToggleTask(task)
                        },
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                if (task.isCompleted) Color(0xFFF3F4F6) else Color.White
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                ) {
                  Row(
                      modifier = Modifier.fillMaxWidth().padding(16.dp),
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.CenterVertically,
                  ) {
                    Column(modifier = Modifier.weight(1f)) {
                      Text(
                          text = task.title,
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Medium,
                          color = if (task.isCompleted) Color.Gray else Color(0xFF111827),
                          textDecoration =
                              if (task.isCompleted) TextDecoration.LineThrough
                              else TextDecoration.None,
                      )
                      Spacer(modifier = Modifier.height(4.dp))
                      Text(text = task.category, fontSize = 12.sp, color = Color.Gray)
                    }
                    Text(
                        text = if (task.isCompleted) "DONE" else " ACTIVE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (task.isCompleted) Color(0xFF059669) else Color(0xFFDC2626),
                        modifier = Modifier.padding(start = 12.dp),
                    )
                  }
                }
              }
            }
          }
        }

        FloatingActionButton(
            onClick = { onNavigateToInput() },
            modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp),
            containerColor = Color(0xFF2563EB),
            contentColor = Color.White,
        ) {
          Icon(
              painter = painterResource(id = R.drawable.rounded_add_box_24),
              contentDescription = "Navigate to Input Screen",
          )
        }
      }
    }
