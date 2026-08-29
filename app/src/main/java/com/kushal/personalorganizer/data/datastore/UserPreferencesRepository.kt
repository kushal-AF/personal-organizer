package com.kushal.personalorganizer.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val USER_NAME = stringPreferencesKey("user_name")
    }

    val userName: Flow<String?> = dataStore.data.map { prefs -> prefs[USER_NAME] }

    suspend fun setUserName(name: String) {
        dataStore.edit { prefs -> prefs[USER_NAME] = name }
    }
}