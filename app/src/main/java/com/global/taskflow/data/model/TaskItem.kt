package com.global.taskflow.data.model

import java.util.UUID

/**
 * TaskItem serves as our primary immutable data container tracking individual task items inside the
 * TaskFlow application architecture.
 */
data class TaskItem(
    val id: UUID = UUID.randomUUID(), // Generates an absolute unique system identifier
    val title: String, // Holds the core description of our task obligation
    val category: String, // Tracks the structural organization bucket label
    val isCompleted: Boolean = false, // Flags whether the task record is checked off
)
