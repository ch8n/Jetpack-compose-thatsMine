package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs

import android.content.Context
import androidx.compose.runtime.produceState
import androidx.datastore.preferences.clear
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.ch8n.thatsmine.base.utils.getValue
import com.ch8n.thatsmine.base.utils.setValue
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PREF_RESTORE_FILE_NAME
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PrefsDataStore
import com.ch8n.thatsmine.domain.models.Origin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber


interface RestoreFormStore {
    fun clear(): Flow<Boolean>
    var itemName: Flow<String>
    var itemDescription: Flow<String>
    var originName: Flow<String>
    var ownedAt: Flow<Long>
    var expiresIn: Flow<Long>
    var origin: Flow<Origin>
    var inventory: Flow<Int>
    var isFavourite: Flow<Boolean>
}


class RestoreFormDataStoreImpl(
    context: Context,
    fileName: String = PREF_RESTORE_FILE_NAME
) : RestoreFormStore, PrefsDataStore(context.applicationContext, fileName) {

    companion object {
        private val ITEM_NAME = preferencesKey<String>("ITEM_NAME")
        private val ITEM_DESC = preferencesKey<String>("ITEM_DESC")
        private val ORIGIN_NAME = preferencesKey<String>("ORIGIN_NAME")
        private val ORIGIN_TYPE = preferencesKey<Int>("ORIGIN_TYPE")
        private val OWNED_TIME = preferencesKey<Long>("OWNED_TIME")
        private val EXPIRE_TIME = preferencesKey<Long>("EXPIRE_TIME")
        private val INVENTORY = preferencesKey<Int>("INVENTORY")
        private val IS_FAVOURITE = preferencesKey<Boolean>("IS_FAVOURITE")
    }

    override fun clear(): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.edit {
                it.clear()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override var itemName: Flow<String>
        get() = dataStore.getValue(ITEM_NAME, "")
        set(value) {
            value.map { input ->
                dataStore.setValue(ITEM_NAME, input)
            }
        }

    override var itemDescription: Flow<String>
        get() = dataStore.getValue(ITEM_DESC, "")
        set(value) {
            value.map { input ->
                dataStore.setValue(ITEM_DESC, input)
            }
        }

    override var originName: Flow<String>
        get() = dataStore.getValue(ORIGIN_NAME, "")
        set(value) {
            value.map { input ->
                dataStore.setValue(ORIGIN_NAME, input)
            }
        }


    override var ownedAt: Flow<Long>
        get() = dataStore.getValue(OWNED_TIME, 0)
        set(value) {
            value.map { input ->
                dataStore.setValue(OWNED_TIME, input)
            }
        }

    override var expiresIn: Flow<Long>
        get() = dataStore.getValue(EXPIRE_TIME, 0)
        set(value) {
            value.map { input ->
                dataStore.setValue(EXPIRE_TIME, input)
            }
        }

    override var origin: Flow<Origin>
        get() = dataStore.getValue(ORIGIN_TYPE, -1).map {
            when (it) {
                0 -> Origin.PURCHASED
                1 -> Origin.GIFTED
                else -> Origin.UNDEFINED
            }
        }
        set(value) {
            value.map { input ->
                val perfOrigin = when (input) {
                    Origin.UNDEFINED -> -1
                    Origin.GIFTED -> 1
                    Origin.PURCHASED -> 0
                }
                dataStore.setValue(ORIGIN_TYPE, perfOrigin)
            }
        }

    override var inventory: Flow<Int>
        get() = dataStore.getValue(INVENTORY, 0)
        set(value) {
            value.map { input ->
                dataStore.setValue(INVENTORY, input)
            }
        }

    override var isFavourite: Flow<Boolean>
        get() = dataStore.getValue(IS_FAVOURITE, false)
        set(value) {
            value.map { input ->
                dataStore.setValue(IS_FAVOURITE, input)
            }
        }
}




