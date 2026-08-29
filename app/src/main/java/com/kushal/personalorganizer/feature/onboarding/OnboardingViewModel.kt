package com.kushal.personalorganizer.feature.onboarding

import androidx.lifecycle.ViewModel
import com.kushal.personalorganizer.data.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: UserPreferencesRepository
) : ViewModel() {
    suspend fun saveNameAndWait(name: String) {
        if (name.isNotBlank()) {
            repository.setUserName(name.trim())
        }
    }
}