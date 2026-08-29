package com.kushal.personalorganizer.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kushal.personalorganizer.feature.onboarding.OnboardingScreen
import com.kushal.personalorganizer.ui.theme.AppBackground

@Composable
fun AppRoot(
    appViewModel: AppViewModel = hiltViewModel()
) {
    val uiState by appViewModel.uiState.collectAsState()

    when (uiState) {
        is AppUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize().background(AppBackground))
        }
        is AppUiState.NeedsOnboarding -> {
            OnboardingScreen(onDone = { })
        }
        is AppUiState.Ready -> {
            MainNavigation()
        }
    }
}