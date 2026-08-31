package com.kushal.personalorganizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance_subjects")
data class AttendanceSubject(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val totalClasses: Int = 0,
    val attendedClasses: Int = 0,
    val minPercentage: Int = 75
)

@Entity(tableName = "timetable_entries")
data class TimetableEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val day: String,        // "Monday".."Sunday"
    val startHour: Int,     // 0-23
    val startMinute: Int,   // 0,15,30,45
    val endHour: Int,
    val endMinute: Int,
    val subjectName: String,
    val room: String = "",
    val remindersOn: Boolean = true
)

@Entity(tableName = "semesters")
data class Semester(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

@Entity(tableName = "course_grades")
data class CourseGrade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val semesterId: Int,
    val courseName: String,
    val creditHours: Double,
    val grade: String   // "O","A+","A","B+","B","C","P","F"
)

@Entity(tableName = "assignments")
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subject: String = "",
    val dueDateMillis: Long,
    val isCompleted: Boolean = false
)