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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.global.taskflow.data.model.TaskItem

/** TaskStatusBadge renders a highly polished visual indicator chip for a task's status. */
@Composable
fun TaskStatusBadge(
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
) {
  Text(
      text = if (isCompleted) "\u2713 DONE" else "\u274f ACTIVE",
      style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
      color =
          if (isCompleted) MaterialTheme.colorScheme.onPrimaryContainer
          else MaterialTheme.colorScheme.error,
      modifier =
          modifier
              .background(
                  color =
                      if (isCompleted) MaterialTheme.colorScheme.primaryContainer
                      else MaterialTheme.colorScheme.errorContainer,
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
              containerColor =
                  if (task.isCompleted) MaterialTheme.colorScheme.surfaceVariant
                  else MaterialTheme.colorScheme.surface
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
            style = MaterialTheme.typography.titleMedium,
            color =
                if (task.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant
                else MaterialTheme.colorScheme.onSurface,
            textDecoration =
                if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = task.category,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
