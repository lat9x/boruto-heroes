package com.tuan.borutoheroes.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.tuan.borutoheroes.domain.repository.DataStoreOperations
import com.tuan.borutoheroes.util.Constants.PREFERENCES_KEY
import com.tuan.borutoheroes.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// get the saved file from the device
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class DataStoreOperationsImpl(
    context: Context
) : DataStoreOperations {

    // specify which key to save
    private object PreferencesKey {
        val onBoardingKey: Preferences.Key<Boolean> = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore: DataStore<Preferences> = context.dataStore

    override suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = isCompleted
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false

                // return the read value
                onBoardingState
            }
    }
}