package com.global.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.global.taskflow.ui.screens.DashboardScreen
import com.global.taskflow.ui.screens.InputScreen

/**
 * TaskFlowGraph links our type-safe route paths to our isolated screen layouts, orchestrating
 * visibility states across a unified application backstack.
 */
@Composable
fun TaskFlowNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
  // NavHost acts as the physical viewport window container linked to our controller
  NavHost(
      navController = navController,
      startDestination = Screen.Dashboard.routePath, // Initial home destination path
      modifier = modifier,
  ) {
    // Destination Node 1: The Primary Task Dashboard Screen View
    composable(route = Screen.Dashboard.routePath) {
      DashboardScreen(
          onNavigateToInput = {
            // Controller pushes the input destination onto the active backstack
            navController.navigate(Screen.TaskInput.routePath)
          }
      )
    }
    // Destination Node 2: The Task Creation Form Entry Screen View
    composable(route = Screen.TaskInput.routePath) {
      InputScreen(
          onNavigateBack = {
            // Controller pops the input view off the stack to reveal the dashboard
            navController.popBackStack()
          }
      )
    }
  }
}
