package com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore

data class StoreConfig<T>(
    val fileName: String,
    val serializer: Serializer<T>
)

abstract class ProtoDataStore<T>(context: Context, config: StoreConfig<T>) {

    protected val dataStore: DataStore<T> = context.createDataStore(
        fileName = config.fileName,
        serializer = config.serializer
    )
}

abstract class PrefsDataStore(context: Context, fileName: String) {
    protected val dataStore: DataStore<Preferences> = context.createDataStore(
        name = fileName
    )
}