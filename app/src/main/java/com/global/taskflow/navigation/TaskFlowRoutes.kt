package com.global.taskflow.navigation

/**
 * TaskFlowRoutes defines the type-safe structural destinations available within our application
 * navigation graph.
 */
sealed class Screen(val routePath: String) {
  /** Represents the primary home dashboard view listing active task cards. */

  // 1st Route
  object Dashboard : Screen("task_dashboard")

  // 2nd Route
  /** Represents the date entry form view designed to capture and validate fresh tasks. */
  object TaskInput : Screen("task_input")
}

/** NavigationConfig holds centralized operational variables for our routing engine. */
object NavigationConfig {
  const val ANIMATION_DURATION_MS = 300
}
