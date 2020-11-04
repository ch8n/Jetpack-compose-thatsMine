package com.ch8n.thatsmine.data.local.datasource.persistence.prefs

import android.content.Context
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.ch8n.thatsmine.ProtoOwnedItem
import com.ch8n.thatsmine.ProtoOwnedItems
import com.ch8n.thatsmine.ProtoSettings
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.PrefsDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.ProtoDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.StoreConfig
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.protos.SerializersOwnedItem
import com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.protos.SerializersSettings
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import timber.log.Timber
import java.io.IOException


interface SettingStore {
    val currentTheme: Flow<AppTheme>
    val activeUser: Flow<User>
    val enableNotification: Flow<Boolean>
    val isLoggedIn: Flow<Boolean>
    val isGuest: Flow<Boolean>

    suspend fun setCurrentTheme(theme: AppTheme)
    suspend fun setActiveUser(user: User)
    suspend fun setNotification(enabled: Boolean)
    suspend fun setLoggedIn(loggedIn: Boolean)
    suspend fun setGuest(isGuest: Boolean)
}

class SettingsDataStoreImpl(
    context: Context,
    config: StoreConfig<ProtoSettings> = StoreConfig(
        fileName = "settings.pb",
        SerializersSettings
    )
) : SettingStore, ProtoDataStore<ProtoSettings>(
    context.applicationContext,
    config
) {

    private fun ProtoSettings.ActiveUser.toUser(): User {
        return User(
            userId = this.userId,
            userName = this.userName,
            fullName = this.fullName,
            email = this.email
        )
    }

    private fun User.toActiveUser(): ProtoSettings.ActiveUser {
        return ProtoSettings.ActiveUser.newBuilder()
            .setUserId(this.userId)
            .setUserName(this.userName)
            .setFullName(this.fullName)
            .setEmail(this.email)
            .build()
    }


    override val currentTheme: Flow<AppTheme>
        get() = dataStore.data.map { protoStore ->
            when (protoStore.currentTheme) {
                ProtoSettings.Theme.Dark -> AppTheme.Dark
                ProtoSettings.Theme.Light -> AppTheme.Light
                ProtoSettings.Theme.UNRECOGNIZED -> throw IllegalStateException("error proto store reading currentTheme")
            }
        }

    override val activeUser: Flow<User>
        get() = dataStore.data.map { protoStore ->
            protoStore.activeUser.toUser()
        }

    override val enableNotification: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.notificationEnabled
        }

    override val isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.loggedIn
        }

    override val isGuest: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.guestMode
        }


    override suspend fun setCurrentTheme(theme: AppTheme) {
        dataStore.updateData { store ->
            val storeTheme = when (theme) {
                AppTheme.Light -> ProtoSettings.Theme.Light
                AppTheme.Dark -> ProtoSettings.Theme.Dark
            }
            store.toBuilder().setCurrentTheme(storeTheme).build()
        }
    }

    override suspend fun setActiveUser(user: User) {
        dataStore.updateData { store ->
            val storeUser = user.toActiveUser()
            store.toBuilder().setActiveUser(storeUser).build()
        }
    }

    override suspend fun setNotification(enabled: Boolean) {
        dataStore.updateData { store ->
            store.toBuilder().setNotificationEnabled(enabled).build()
        }
    }

    override suspend fun setLoggedIn(loggedIn: Boolean) {
        dataStore.updateData { store ->
            store.toBuilder().setLoggedIn(loggedIn).build()
        }
    }

    override suspend fun setGuest(isGuest: Boolean) {
        dataStore.updateData { store ->
            store.toBuilder().setGuestMode(isGuest).build()
        }
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



