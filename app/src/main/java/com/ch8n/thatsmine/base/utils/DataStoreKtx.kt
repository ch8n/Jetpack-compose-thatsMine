package com.ch8n.thatsmine.base.utils

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

fun <T> DataStore<Preferences>.getValue(
    key: Preferences.Key<T>,
    defaultValue: T
): Flow<T> {
    return data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            println("getting value")
            preferences[key] ?: defaultValue
        }
}

suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
    edit { preferences ->
        preferences[key] = value
    }
    println("value updated")
}