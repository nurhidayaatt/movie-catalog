package com.nurhidayaatt.core.presentation

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

class PreferencesManager(context: Context) {

    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("user_preferences") }
    )

    val preferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "Error reading preferences")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            SortType.valueOf(
                preferences[PreferencesKeys.SORT_TYPE] ?: SortType.ID.name
            )
        }

    suspend fun updateSortType(sortOrder: SortType) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SORT_TYPE] = sortOrder.name
        }
    }

    private object PreferencesKeys {
        val SORT_TYPE = stringPreferencesKey("sort_type")
    }
}