package com.global.taskflow.data.local.converters

import androidx.room.TypeConverter
import java.util.UUID

/**
 * TaskConverters provides explicit translation routines for the Room Database, allowing complex
 * Kotlin attributes to map seamlessly into SQLite database columns.
 */
class TaskConverters {

  /** Converts a high-level UUID instance into a primitive SQLite text string. */
  class TaskConverters {

    /** Converts a high-level UUID instance into a primitive SQLite text string. */
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
      return uuid?.toString()
    }

    /** Parses a primitive SQLite text string back into a structural UUID instance. */
    fun toUUID(uuidString: String?): UUID? {
      return uuidString?.let { UUID.fromString(it) }
    }
  }
}
