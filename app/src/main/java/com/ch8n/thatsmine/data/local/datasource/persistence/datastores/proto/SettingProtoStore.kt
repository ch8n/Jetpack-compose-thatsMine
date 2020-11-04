package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import android.content.Context
import com.ch8n.thatsmine.ProtoSettings
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PROTO_SETTINGS_FILE_NAME
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.ProtoDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingProtoStore {
    var currentTheme: Flow<AppTheme>
    var activeUser: Flow<User>
    var enableNotification: Flow<Boolean>
    var isLoggedIn: Flow<Boolean>
    var isGuest: Flow<Boolean>
}

class SettingsProtoStoreImpl(
    context: Context,
    config: StoreConfig<ProtoSettings> = StoreConfig(
        fileName = PROTO_SETTINGS_FILE_NAME,
        SerializersSettings
    )
) : SettingProtoStore, ProtoDataStore<ProtoSettings>(
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

    override var currentTheme: Flow<AppTheme>
        get() = dataStore.data.map { protoStore ->
            when (protoStore.currentTheme) {
                ProtoSettings.Theme.Dark -> AppTheme.Dark
                ProtoSettings.Theme.Light -> AppTheme.Light
                ProtoSettings.Theme.UNRECOGNIZED -> throw IllegalStateException("error proto store reading currentTheme")
            }
        }
        set(value) {
            value.map { theme ->
                dataStore.updateData { store ->
                    val storeTheme = when (theme) {
                        AppTheme.Light -> ProtoSettings.Theme.Light
                        AppTheme.Dark -> ProtoSettings.Theme.Dark
                    }
                    store.toBuilder().setCurrentTheme(storeTheme).build()
                }
            }
        }

    override var activeUser: Flow<User>
        get() = dataStore.data.map { protoStore ->
            protoStore.activeUser.toUser()
        }
        set(value) {
            value.map { user ->
                dataStore.updateData { store ->
                    val storeUser = user.toActiveUser()
                    store.toBuilder().setActiveUser(storeUser).build()
                }
            }
        }

    override var enableNotification: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.notificationEnabled
        }
        set(value) {
            value.map { enabled ->
                dataStore.updateData { store ->
                    store.toBuilder().setNotificationEnabled(enabled).build()
                }
            }
        }

    override var isLoggedIn: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.loggedIn
        }
        set(value) {
            value.map { loggedIn ->
                dataStore.updateData { store ->
                    store.toBuilder().setLoggedIn(loggedIn).build()
                }
            }
        }

    override var isGuest: Flow<Boolean>
        get() = dataStore.data.map { protoStore ->
            protoStore.guestMode
        }
        set(value) {
            value.map { isGuest ->
                dataStore.updateData { store ->
                    store.toBuilder().setGuestMode(isGuest).build()
                }
            }
        }

}

