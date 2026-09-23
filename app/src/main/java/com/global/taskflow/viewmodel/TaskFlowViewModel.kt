package com.global.taskflow.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.global.taskflow.data.model.TaskItem

/**
 * TaskFlowViewModel serves as the centralized command center for our application logic. It manages
 * state transformations and retains data safely across layout alterations.
 */
class TaskFlowViewModel : ViewModel() {

  // Internal mutable collection tracking our active task data records in memory
  private val _tasks = mutableStateListOf<TaskItem>()

  // Exposing a read-only list reference to prevent outside layout files from mutating our state
  // directly
  val tasks: List<TaskItem>
    get() = _tasks

  /**
   * Accepts raw input text arguments, validates parameters, and appends a fresh task object to our
   * state list.
   *
   * @param title The primary text description text entered by the user.
   * @param category The classification category label assigned to the item record.
   */
  fun addTask(title: String, category: String) {
    // Enforcing core business validation rules to prevent empty entries

    if (title.isBlank()) return

    val freshTask =
        TaskItem(
            title = title.trim(),
            category = category,
        )
    _tasks.add(freshTask)
  }
}
