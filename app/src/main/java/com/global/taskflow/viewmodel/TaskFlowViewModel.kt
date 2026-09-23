package com.global.taskflow.viewmodel

import androidx.lifecycle.ViewModel
import com.global.taskflow.data.model.TaskItem
import kotlinx.coroutines.flow.*

/**
 * An upgraded version of TaskFlowViewMode3l leveraging Kotlin StateFlow streams to coordinate
 * reactive task data states across our presentation screens cleanly.
 */
class TaskFlowViewModel : ViewModel() {

  // 1. Internal mutable stream container initialized with an empty task collection wrapper
  private val _tasksState = MutableStateFlow<List<TaskItem>>(emptyList())

  // 2. Public read-only StateFlow exposed cleanly to our user interface listeners
  val tasksState: StateFlow<List<TaskItem>> = _tasksState.asStateFlow()

  /**
   * Validates input parameters and atomically updates the underlying state flow stream.
   *
   * @param title The description text captured from our user input text fields.
   * @param category The sorting category tag assigned by the user.
   */
  fun addTask(title: String, category: String) {
    if (title.isBlank()) return

    val freshTask =
        TaskItem(
            title = title.trim(),
            category = category,
        )

    // 3. Atomically overwrite the stream state wrapper with a clean immutable list addition
    _tasksState.update { currentTasksList ->
      currentTasksList + freshTask
    }
  }
}
