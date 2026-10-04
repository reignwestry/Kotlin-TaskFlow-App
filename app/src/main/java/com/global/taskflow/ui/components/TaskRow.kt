package com.global.taskflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.global.taskflow.data.model.TaskItem

/** TaskStatusBadge renders a highly polished visual indicator chip for a task's status. */
@Composable
fun TaskStatusBadge(
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
) {
  Text(
      text = if (isCompleted) "DONE" else "ACTIVE",
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      color = if (isCompleted) Color(0xFF059669) else Color(0xFFDC2626),
      modifier =
          modifier
              .background(
                  color = if (isCompleted) Color(0xFFD1FAE5) else Color(0xFFFEE2E2),
                  shape = RoundedCornerShape(6.dp),
              )
              .padding(horizontal = 8.dp, vertical = 4.dp),
  )
}

/** TaskRow displays an isolated, configurable item entry row for our task arrays. */
@Composable
fun TaskRow(
    task: TaskItem,
    onRowClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
  Card(
      modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).clickable { onRowClick() },
      colors =
          CardDefaults.cardColors(
              containerColor = if (task.isCompleted) Color(0xFFF3F4F6) else Color.White
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
                if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = task.category,
            fontSize = 12.sp,
            color = Color.Gray,
        )
      }

      // Reusing our dedicated status badge asset
      TaskStatusBadge(
          isCompleted = task.isCompleted,
          modifier = Modifier.padding(start = 12.dp),
      )
    }
  }
}
