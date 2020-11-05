package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore


/***
 * Do not instrument AndroidX DataStore
 * Reason : https://github.com/robolectric/robolectric/pull/5987
 * [edit 1 - 5/nov/2020]: can be tested on Roboelectric-4.5-alpha-2
 */

// file names for dataStores
const val PREF_RESTORE_FILE_NAME = "RestoreFormStore"
const val PROTO_OWNED_ITEM_FILE_NAME = "ownedItem.pb"
const val PROTO_SETTINGS_FILE_NAME = "settings.pb"

// config object
data class StoreConfig<T>(
    val fileName: String,
    val serializer: Serializer<T>
)

// Skeletal classes for Proto-DataStore
abstract class ProtoDataStore<T>(context: Context, config: StoreConfig<T>) {
    protected val dataStore: DataStore<T> = context.createDataStore(
        fileName = config.fileName,
        serializer = config.serializer
    )
}

// Skeletal classes for Preference-DataStore
abstract class PrefsDataStore(context: Context, fileName: String) {
    internal val dataStore: DataStore<Preferences> = context.createDataStore(
        name = fileName
    )
}