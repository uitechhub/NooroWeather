package com.ivantest.nooroweather.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore for queries.
 */
private val Context.preferencesDataStore by preferencesDataStore(name = "nooro_weather_store")


class DataStoreManager(private val context: Context) {
    companion object {
        private val LAST_SEARCHED_CITY_KEY = stringPreferencesKey("last_searched_city")
    }

    val lastSearchedCity: Flow<String?> = context.preferencesDataStore.data.map { preferences ->
        preferences[LAST_SEARCHED_CITY_KEY]
    }

    suspend fun saveLastSearchedCity(city: String) {
        context.preferencesDataStore.edit { preferences ->
            preferences[LAST_SEARCHED_CITY_KEY] = city
        }
    }

}