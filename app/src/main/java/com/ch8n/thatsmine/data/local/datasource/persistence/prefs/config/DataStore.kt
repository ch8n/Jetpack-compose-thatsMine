package com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import com.ch8n.thatsmine.OwnedItems
import com.ch8n.thatsmine.ProtoOwnedItems
import com.ch8n.thatsmine.ProtoSettings
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.protos.SerializersOwnedItem
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.protos.SerializersSettings
import kotlinx.coroutines.runBlocking


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


/***
 * TODO
 *  1. create a data store
 *      a. settings data store
 *          -> current theme [dark/light]
 *          -> current user account [create current user model]
 *          -> enable notifications
 *          -> loggedIn? or GuestMode?
 */

class SettingsDataStoreImpl(
    context: Context,
    config: StoreConfig<ProtoSettings> = StoreConfig(
        fileName = "settings.pb",
        SerializersSettings
    )
) : ProtoDataStore<ProtoSettings>(context.applicationContext, config) {


    companion object {
        private val THEME = preferencesKey<String>("THEME")
        private val USER = preferencesKey<String>("USER")
        private val NOTIFICATION = preferencesKey<String>("NOTIFICATION")
        private val LOGGEDIN = preferencesKey<String>("LOGGEDIN")
        private val GUEST = preferencesKey<String>("GUEST")
    }
}


/***
 * TODO
 *  1. create a data store
 *      b. owned item data store or persistance
 *          -> last view item
 *          -> store list of item [atmost = 10]
 */
class OwnedItemDataStore(
    context: Context,
    config: StoreConfig<ProtoOwnedItems> = StoreConfig(
        fileName = "ownedItem.pb",
        SerializersOwnedItem
    )
) : ProtoDataStore<ProtoOwnedItems>(context.applicationContext, config) {

    companion object {
        private val USERNAME = preferencesKey<String>("username")

    }
}


/***
 * TODO
 *  1. create a data store
 *      c. form restore data store
 *          -> last editing item fields value
 */
class RestoreFormDataStore(
    context: Context,
    fileName: String = "RestoreFormStore"
) : PrefsDataStore(context.applicationContext, fileName) {

    companion object {
        private val THEME = preferencesKey<String>("THEME")
        private val USER = preferencesKey<String>("THEME")


    }
}



