package com.kushal.personalorganizer.data.repository

import com.kushal.personalorganizer.data.local.dao.ClassWorkDao
import com.kushal.personalorganizer.data.local.entity.ClassWorkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassWorkRepository @Inject constructor(
    private val dao: ClassWorkDao
) {
    fun getAll(): Flow<List<ClassWorkEntity>> = dao.getAll()

    suspend fun add(title: String) {
        dao.insert(ClassWorkEntity(title = title))
    }

    suspend fun toggle(item: ClassWorkEntity) {
        dao.update(item.copy(isCompleted = !item.isCompleted))
    }

    suspend fun delete(item: ClassWorkEntity) {
        dao.delete(item)
    }
}