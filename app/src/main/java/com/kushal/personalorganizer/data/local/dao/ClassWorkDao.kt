package com.kushal.personalorganizer.data.local.dao

import androidx.room.*
import com.kushal.personalorganizer.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassWorkDao {

    // ---------- Attendance ----------
    @Query("SELECT * FROM attendance_subjects ORDER BY name ASC")
    fun getAttendanceSubjects(): Flow<List<AttendanceSubject>>

    @Insert
    suspend fun insertAttendanceSubject(subject: AttendanceSubject)

    @Update
    suspend fun updateAttendanceSubject(subject: AttendanceSubject)

    @Delete
    suspend fun deleteAttendanceSubject(subject: AttendanceSubject)

    // ---------- Timetable ----------
    @Query("SELECT * FROM timetable_entries ORDER BY startHour ASC, startMinute ASC")
    fun getTimetableEntries(): Flow<List<TimetableEntry>>

    @Insert
    suspend fun insertTimetableEntry(entry: TimetableEntry)

    @Insert
    suspend fun insertTimetableEntries(entries: List<TimetableEntry>)

    @Delete
    suspend fun deleteTimetableEntry(entry: TimetableEntry)

    @Query("DELETE FROM timetable_entries")
    suspend fun clearTimetable()

    // ---------- Semesters ----------
    @Query("SELECT * FROM semesters ORDER BY id ASC")
    fun getSemesters(): Flow<List<Semester>>

    @Insert
    suspend fun insertSemester(semester: Semester): Long

    @Delete
    suspend fun deleteSemester(semester: Semester)

    // ---------- Course grades ----------
    @Query("SELECT * FROM course_grades ORDER BY id ASC")
    fun getAllCourseGrades(): Flow<List<CourseGrade>>

    @Insert
    suspend fun insertCourseGrade(course: CourseGrade)

    @Delete
    suspend fun deleteCourseGrade(course: CourseGrade)

    // ---------- Assignments ----------
    @Query("SELECT * FROM assignments ORDER BY dueDateMillis ASC")
    fun getAssignments(): Flow<List<AssignmentEntity>>

    @Insert
    suspend fun insertAssignment(assignment: AssignmentEntity)

    @Update
    suspend fun updateAssignment(assignment: AssignmentEntity)

    @Delete
    suspend fun deleteAssignment(assignment: AssignmentEntity)
}