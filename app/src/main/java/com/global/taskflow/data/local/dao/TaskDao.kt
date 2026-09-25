package com.global.taskflow.data.local.dao

import androidx.room.*
import com.global.taskflow.data.model.TaskItem
import kotlinx.coroutines.flow.Flow

/**
 * TaskDao defines the transaction pathways and SQL database execution queries permitted against our
 * persistent local tasks table storage.
 */
@Dao
interface TaskDao {

  /**
   * Streams the complete collection of logged tasks sequentially from disk. Returns an asynchronous
   * Flow data stream that emits fresh list updates instantly.
   */
  @Query("SELECT*FROM tasks_table ORDER BY isCompleted ASC")
  fun getAllTasksStream(): Flow<List<TaskItem>>

  /**
   * Inserts a fresh task record entry into the local database table. If a matching PrimaryKey
   * collision occurs, it automatically overwrites the old row.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertTaskRecord(task: TaskItem)

  /** Wipes out a specific task entry row from our persistent storage tables. */
  @Query("DELETE FROM tasks_table WHERE id = :taskId") suspend fun deleteTaskById(taskId: String)
}
