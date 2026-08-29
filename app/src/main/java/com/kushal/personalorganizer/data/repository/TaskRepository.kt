package com.kushal.personalorganizer.data.repository

import com.kushal.personalorganizer.data.local.dao.TaskDao
import com.kushal.personalorganizer.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addTask(title: String) {
        taskDao.insertTask(TaskEntity(title = title))
    }

    suspend fun toggleTaskCompletion(task: TaskEntity) {
        taskDao.updateTask(task.copy(isCompleted = !task.isCompleted))
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}