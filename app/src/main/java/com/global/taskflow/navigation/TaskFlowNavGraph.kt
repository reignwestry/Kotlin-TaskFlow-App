package com.global.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.global.taskflow.ui.screens.DashboardScreen
import com.global.taskflow.ui.screens.InputScreen
import com.global.taskflow.viewmodel.TaskFlowViewModel

/**
 * TaskFlowGraph links our type-safe route paths to our isolated screen layouts, orchestrating
 * visibility states across a unified application backstack.
 */
@Composable
fun TaskFlowNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    taskViewModel: TaskFlowViewModel = viewModel(), // Instantiating our centralized state engine
) {
  // Collect state safely while automatically respecting the active screen lifecycle
  val tasksListState by taskViewModel.tasksState.collectAsStateWithLifecycle()

  NavHost(
      navController = navController,
      startDestination = Screen.Dashboard.routePath, // Initial home destination path
      modifier = modifier,
  ) {
    // Destination Node 1: The Primary Task Dashboard Screen View
    composable(route = Screen.Dashboard.routePath) {
      DashboardScreen(
          tasks = tasksListState, // Passing the live data array down to the layout
          onNavigateToInput = {
            // Controller pushes the input destination onto the active backstack
            navController.navigate(Screen.TaskInput.routePath)
          },
      )
    }
    // Destination Node 2: The Task Creation Form Entry Screen View
    composable(route = Screen.TaskInput.routePath) {
      InputScreen(
          onSaveTask = { verifiedTitle ->
            // Dispatching user text input events traight to our business logic
            taskViewModel.addTask(title = verifiedTitle, category = "Engineering")
            navController.popBackStack()
          },
          onNavigateBack = {
            // Controller pops the input view off the stack to reveal the dashboard
            navController.popBackStack()
          },
      )
    }
  }
}
