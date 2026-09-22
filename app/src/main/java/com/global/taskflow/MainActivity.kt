package com.global.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.global.taskflow.navigation.TaskFlowNavGraph

/**
 * MainActivity serves as the absolute operating system entry point for TaskFlow. This class binds
 * our platform window shell directly to the centralized routing framework, launching our complete
 * multi-screen application lifecycle.
 */
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // setContent initializes the Jetpack Compose compilation environment
    setContent {
      MaterialTheme {
        // Surface adapts our container canvas beautifully to dark and light modes
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
          // Bootstrapping our complete, multi-screen navigation engine
          TaskFlowNavGraph()
        }
      }
    }
  }
}
