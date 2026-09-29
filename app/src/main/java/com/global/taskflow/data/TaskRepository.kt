package com.global.taskflow.data.repository

import com.global.taskflow.data.local.dao.TaskDao
import com.global.taskflow.data.model.TaskItem
import kotlinx.coroutines.flow.Flow

/**
 * TaskRepository acts as our clean data broker layer, isolating our business logic engines from
 * low-level database operations.
 */
class TaskRepository(private val taskDao: TaskDao) {

  /** Exposes our live, reactive database stream directly to our state controllers. */
  val allTasksStream: Flow<List<TaskItem>> = taskDao.getAllTasksStream()

  /**
   * Executes a non-blocking database insert command to save a task record onto the storage disk.
   */
  suspend fun saveTaskRecord(task: TaskItem) {
    taskDao.insertTaskRecord(task)
  }
}
