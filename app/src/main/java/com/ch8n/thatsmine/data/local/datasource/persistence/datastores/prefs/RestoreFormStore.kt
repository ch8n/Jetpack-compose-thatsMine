package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs

import android.content.Context
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
    val itemName: Flow<String>
    val itemDescription: Flow<String>
    val originName: Flow<String>
    val ownedAt: Flow<Long>
    val expiresIn: Flow<Long>
    val origin: Flow<Origin>
    val inventory: Flow<Int>
    val isFavourite: Flow<Boolean>

    fun setItemName(itemName: String): Flow<Boolean>
    fun setItemDescription(itemDescription: String): Flow<Boolean>
    fun setOriginName(originName: String): Flow<Boolean>
    fun setOwnedAt(ownedAt: Long): Flow<Boolean>
    fun setExpiresIn(expiresIn: Long): Flow<Boolean>
    fun setOrigin(origin: Origin): Flow<Boolean>
    fun setInventory(inventory: Int): Flow<Boolean>
    fun setFavourite(isFavourite: Boolean): Flow<Boolean>
}


class RestoreFormDataStoreImpl(
    context: Context,
    fileName: String = PREF_RESTORE_FILE_NAME
) : RestoreFormStore, PrefsDataStore(context.applicationContext, fileName) {

    companion object {
        private const val defaultString = ""
        private const val defaultInt = 0
        private const val defaultLong = defaultInt.toLong()
        internal val ITEM_NAME = preferencesKey<String>("ITEM_NAME")
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


    override val itemName: Flow<String>
        get() = dataStore.getValue(ITEM_NAME, defaultString)


    override val itemDescription: Flow<String>
        get() = dataStore.getValue(ITEM_DESC, defaultString)


    override val originName: Flow<String>
        get() = dataStore.getValue(ORIGIN_NAME, defaultString)


    override val ownedAt: Flow<Long>
        get() = dataStore.getValue(OWNED_TIME, defaultLong)


    override val expiresIn: Flow<Long>
        get() = dataStore.getValue(EXPIRE_TIME, defaultLong)


    override val origin: Flow<Origin>
        get() = dataStore.getValue(ORIGIN_TYPE, defaultInt).map {
            when (it) {
                0 -> Origin.PURCHASED
                1 -> Origin.GIFTED
                else -> Origin.UNDEFINED
            }
        }


    override val inventory: Flow<Int>
        get() = dataStore.getValue(INVENTORY, defaultInt)


    override val isFavourite: Flow<Boolean>
        get() = dataStore.getValue(IS_FAVOURITE, false)


    override fun setItemName(itemName: String): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(ITEM_NAME, itemName)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setItemDescription(itemDescription: String): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(ITEM_DESC, itemDescription)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setOriginName(originName: String): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(ORIGIN_NAME, originName)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setOwnedAt(ownedAt: Long): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(OWNED_TIME, ownedAt)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setExpiresIn(expiresIn: Long): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(EXPIRE_TIME, expiresIn)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setOrigin(origin: Origin): Flow<Boolean> {
        return flow<Boolean> {
            val perfOrigin = when (origin) {
                Origin.UNDEFINED -> -1
                Origin.GIFTED -> 1
                Origin.PURCHASED -> 0
            }
            dataStore.setValue(ORIGIN_TYPE, perfOrigin)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setInventory(inventory: Int): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(INVENTORY, inventory)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setFavourite(isFavourite: Boolean): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.setValue(IS_FAVOURITE, isFavourite)
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }
}




