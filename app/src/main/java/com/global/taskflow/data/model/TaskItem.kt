package com.global.taskflow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * TaskItem serves as our primary immutable data container tracking individual task items inside the
 * TaskFlow application architecture.
 */
@Entity(tableName = "tasks_table")
data class TaskItem(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(), // Explicitly designated as our absolute unique key column
    val title: String, // Captures the structural task description string
    val category: String, // Tracks the visual organization bucket label
    val isCompleted: Boolean = false, // Flags whether the task record is checked off
)
