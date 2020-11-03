package com.ch8n.thatsmine.data.local.datasource.persistance.dataStore.config

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import com.ch8n.thatsmine.base.utils.getValue
import com.ch8n.thatsmine.base.utils.setValue
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking

/***
 * TODO
 *  1. create a data store
 *      a. settings data store
 *          -> current theme [dark/light]
 *          -> current user account [create current user model]
 *          -> enable notifications
 *          -> loggedIn? or GuestMode?
 *      b. owned item data store or persistance
 *          -> last view item
 *          -> store list of item [atmost = 10]
 *      c. form restore data store
 *          -> last editing item fields value
 */
class OwnedItemDataStore(context: Context, fileName: String) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(fileName)

    fun testSetValue() = runBlocking {
        dataStore.setValue(USERNAME, "Chetan")
    }

    fun testGetValue() = runBlocking {
        val userName = dataStore.getValue(USERNAME, "")
            .catch { error ->
                error.printStackTrace()
                emit(error.message ?: "something went wrong")
            }
            .singleOrNull()
    }

    companion object {
        private val USERNAME = preferencesKey<String>("username")


    }

}