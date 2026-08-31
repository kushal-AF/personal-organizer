package com.kushal.personalorganizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable_entries")
data class TimetableEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dayOfWeek: Int,      // 1 = Monday ... 7 = Sunday
    val startHour: Int,      // 0-23
    val startMinute: Int,    // 0-59
    val endHour: Int,
    val endMinute: Int,
    val className: String,
    val location: String = ""
)