package com.global.taskflow.data.local

import androidx.room.*
import com.global.taskflow.data.local.converters.TaskConverters
import com.global.taskflow.data.local.dao.TaskDao
import com.global.taskflow.data.model.TaskItem

/**
 * TaskFlowDatabase serves as our primary persistent database manager. It coordinates connection
 * pooling, verifies schema states, and links our converters.
 */
@Database(
    entities = [TaskItem::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(TaskConverters::class)
abstract class TaskFlowDatabase : RoomDatabase() {

  /** Exposes our data access object interface gateway to our higher repository tiers. */
  abstract fun getTaskDao(): TaskDao
}
