package com.global.taskflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

/**
 * DashboardScreen displays the primary task list workspace.
 *
 * @param onNavigateToInput An event lambda that signals the navigation graph to transition screens.
 */
@Composable
fun DashboardScreen(onNavigateToInput: () -> Unit, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize().background(Color(0xFFF9FAFB))) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
      Text(
          text = "TaskFlow",
          fontSize = 28.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF111827),
      )

      Spacer(modifier = Modifier.height(24.dp))

      Text(
          text = "Your task list is currently empty.",
          fontSize = 16.sp,
          color = Color(0xFF6B7280),
      )
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
