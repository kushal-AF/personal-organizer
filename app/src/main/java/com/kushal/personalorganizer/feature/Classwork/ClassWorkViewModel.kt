package com.kushal.personalorganizer.feature.classeswork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kushal.personalorganizer.data.local.entity.ClassWorkEntity
import com.kushal.personalorganizer.data.repository.ClassWorkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassWorkViewModel @Inject constructor(
    private val repository: ClassWorkRepository
) : ViewModel() {

    val items: StateFlow<List<ClassWorkEntity>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch { repository.add(title) }
    }

    fun toggle(item: ClassWorkEntity) {
        viewModelScope.launch { repository.toggle(item) }
    }

    fun delete(item: ClassWorkEntity) {
        viewModelScope.launch { repository.delete(item) }
    }
}