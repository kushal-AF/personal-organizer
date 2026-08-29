package com.kushal.personalorganizer.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kushal.personalorganizer.data.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed class AppUiState {
    object Loading : AppUiState()
    object NeedsOnboarding : AppUiState()
    data class Ready(val userName: String) : AppUiState()
}

@HiltViewModel
class AppViewModel @Inject constructor(
    repository: UserPreferencesRepository
) : ViewModel() {
    val uiState: StateFlow<AppUiState> = repository.userName
        .map { name ->
            if (name.isNullOrBlank()) AppUiState.NeedsOnboarding
            else AppUiState.Ready(name)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppUiState.Loading
        )
}