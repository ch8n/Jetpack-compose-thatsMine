package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import android.content.Context
import com.ch8n.thatsmine.ProtoSettings
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PROTO_SETTINGS_FILE_NAME
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.ProtoDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

interface SettingProtoStore {
    val currentTheme: Flow<AppTheme>
    val activeUser: Flow<User>
    val enableNotification: Flow<Boolean>
    val isLoggedIn: Flow<Boolean>
    val isGuest: Flow<Boolean>

    fun clear(): Flow<Boolean>
    fun setCurrentTheme(currentTheme: AppTheme): Flow<Boolean>
    fun setActiveUser(activeUser: User): Flow<Boolean>
    fun setNotification(enableNotification: Boolean): Flow<Boolean>
    fun setLoggedIn(isLoggedIn: Boolean): Flow<Boolean>
    fun setGuestMode(isGuest: Boolean): Flow<Boolean>
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

    override val currentTheme: Flow<AppTheme>
        get() = dataStore.data.map { protoStore ->
            when (protoStore.currentTheme) {
                ProtoSettings.Theme.Dark -> AppTheme.Dark
                ProtoSettings.Theme.Light -> AppTheme.Light
                ProtoSettings.Theme.UNRECOGNIZED -> AppTheme.Light
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

    override fun clear(): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.updateData { store ->
                store.toBuilder().clear().build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }


    override fun setCurrentTheme(currentTheme: AppTheme): Flow<Boolean> {
        return flow {
            dataStore.updateData { store ->
                val storeTheme = when (currentTheme) {
                    AppTheme.Light -> ProtoSettings.Theme.Light
                    AppTheme.Dark -> ProtoSettings.Theme.Dark
                }
                store.toBuilder().setCurrentTheme(storeTheme).build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setActiveUser(activeUser: User): Flow<Boolean> {
        return flow {
            dataStore.updateData { store ->
                val storeUser = activeUser.toActiveUser()
                store.toBuilder().setActiveUser(storeUser).build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setNotification(enableNotification: Boolean): Flow<Boolean> {
        return flow {
            dataStore.updateData { store ->
                store.toBuilder().setNotificationEnabled(enableNotification).build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setLoggedIn(isLoggedIn: Boolean): Flow<Boolean> {
        return flow {
            dataStore.updateData { store ->
                store.toBuilder().setLoggedIn(isLoggedIn).build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setGuestMode(isGuest: Boolean): Flow<Boolean> {
        return flow {
            dataStore.updateData { store ->
                store.toBuilder().setGuestMode(isGuest).build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

}

