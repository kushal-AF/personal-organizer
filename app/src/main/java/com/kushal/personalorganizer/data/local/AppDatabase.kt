package com.kushal.personalorganizer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kushal.personalorganizer.data.local.dao.ClassWorkDao
import com.kushal.personalorganizer.data.local.dao.TaskDao
import com.kushal.personalorganizer.data.local.entity.AssignmentEntity
import com.kushal.personalorganizer.data.local.entity.ClassWorkEntity
import com.kushal.personalorganizer.data.local.entity.CourseGrade
import com.kushal.personalorganizer.data.local.entity.Semester
import com.kushal.personalorganizer.data.local.entity.TaskEntity
import com.kushal.personalorganizer.data.local.entity.TimetableEntry

@Database(
    entities = [
        TaskEntity::class,
        ClassWorkEntity::class,
        `AttendanceSubject.kt`::class,
        TimetableEntry::class,
        Semester::class,
        CourseGrade::class,
        AssignmentEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun classWorkDao(): ClassWorkDao
}