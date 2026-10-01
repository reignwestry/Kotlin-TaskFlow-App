package com.global.taskflow

import android.app.Application
import androidx.room.Room
import com.global.taskflow.data.local.TaskFlowDatabase
import com.global.taskflow.data.repository.TaskRepository

/**
 * TaskFlowApplication acts as the central lifecycle anchor for our application. It initializes and
 * retains our single database instance and data repositories safely.
 */
class TaskFlowApplication : Application() {

  /**
   * Lazy initialization allocates the database container strictly when it is first required,
   * protecting our application boot sequence from performance drops.
   */
  val database: TaskFlowDatabase by lazy {
    Room.databaseBuilder(
            this,
            TaskFlowDatabase::class.java,
            "taskflow_database",
        )
        .build()
  }

  /** Instantiates our repository layer instance, feeding it our managed database DAO gateway. */
  val repository: TaskRepository by lazy {
    TaskRepository(database.getTaskDao())
  }
}
