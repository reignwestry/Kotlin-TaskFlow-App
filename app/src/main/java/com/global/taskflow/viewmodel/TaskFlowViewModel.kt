package com.global.taskflow.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.global.taskflow.TaskFlowApplication
import com.global.taskflow.data.model.TaskItem
import com.global.taskflow.data.repository.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * TaskFlowViewModel coordinates screen state transformations by listening to the local database
 * repository and offloading file write operations onto background execution coroutines.
 */
class TaskFlowViewModel(private val repository: TaskRepository) : ViewModel() {

  /**
   * Reads directly from the repository stream and transforms the collection into an observable
   * StateFlow. WhileSubscribed(5000) keeps the stream active for 5 seconds after screen transitions
   * to prevent reload flickering.
   */
  val tasksState: StateFlow<List<TaskItem>> =
      repository.allTasksStream.stateIn(
          scope = viewModelScope,
          started = SharingStarted.WhileSubscribed(5000),
          initialValue = emptyList(),
      )

  /**
   * Invokes an asynchronous background coroutine scope to write a validated task onto the physical
   * disk.
   */
  fun addTask(title: String, category: String) {
    if (title.isBlank()) return

    val freshTask =
        TaskItem(
            title = title.trim(),
            category = category,
        )

    // Launching an independent background worker thread scope to process database operations safely
    viewModelScope.launch {
      repository.saveTaskRecord(freshTask)
    }
  }

  /**
   * Accepts an active tassk object instance, toggles its execution flag, and dispatches an
   * asynchronous coroutine write block to overwrite the row on disk.
   *
   * @param task The target TaskItem configuration currently selected by the user.
   */
  fun toggleTaskCompletion(task: TaskItem) {
    viewModelScope.launch {
      // Utilizing data class copying tools to modify properties while preserving immutable
      // attributes
      val updatedTask = task.copy(isCompleted = !task.isCompleted)
      repository.saveTaskRecord(updatedTask)
    }
  }

  /**
   * Centralized Factory Blueprint required to securely inject our custom repository dependency
   * straight into the framework's internal ViewModel tracking engine.
   */
  companion object {
    val Factory: ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
          @Suppress("UNCHECKED_CAST")
          override fun create(modelClass: Class<T>, extras: CreationExtras): T {
            // Accessing the application context parameter via the framework configuration extras
            // block
            val application =
                extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    as TaskFlowApplication
            return TaskFlowViewModel(application.repository) as T
          }
        }
  }
}
