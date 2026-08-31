package com.kushal.personalorganizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance_subjects")
data class AttendanceSubject(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val totalClasses: Int = 0,
    val attendedClasses: Int = 0
)