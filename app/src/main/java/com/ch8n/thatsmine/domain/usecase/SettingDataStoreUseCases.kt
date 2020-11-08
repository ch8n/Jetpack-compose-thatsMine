package com.ch8n.thatsmine.domain.usecase

import com.ch8n.thatsmine.base.usecase.BaseUseCase
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto.SettingProtoStore
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber


abstract class SetActiveUserSettingStore(
    protected val activeUser: User
) : BaseUseCase<Boolean>

class SetActiveUserSettingStoreImpl(
    activeUser: User,
    private val settingStore: SettingProtoStore
) : SetActiveUserSettingStore(activeUser) {

    override fun execute(): Flow<Boolean> {
        return settingStore.setActiveUser(activeUser)
            .catch { error ->
                Timber.e(error)
                emit(false)
            }
    }

}

interface GetActiveUserSettingStore : BaseUseCase<User>

class GetActiveUserSettingStoreImpl(
    private val settingStore: SettingProtoStore
) : GetActiveUserSettingStore {

    override fun execute(): Flow<User> {
        return settingStore.activeUser
    }

}


abstract class SetActiveThemeSettingStore(
    protected val appTheme: AppTheme
) : BaseUseCase<Boolean>

class SetActiveThemeSettingStoreImpl(
    appTheme: AppTheme,
    private val settingStore: SettingProtoStore
) : SetActiveThemeSettingStore(appTheme) {

    override fun execute(): Flow<Boolean> {
        return settingStore.setCurrentTheme(appTheme)
            .catch { error ->
                Timber.e(error)
                emit(false)
            }
    }

}

interface GetActiveThemeSettingStore : BaseUseCase<AppTheme>

class GetActiveThemeSettingStoreImpl(
    private val settingStore: SettingProtoStore
) : GetActiveThemeSettingStore {
    override fun execute(): Flow<AppTheme> {
        return settingStore.currentTheme
    }
}


abstract class SetNotificationEnabledSettingStore(
    protected val isNotificationEnabled: Boolean
) : BaseUseCase<Boolean>

class SetNotificationEnabledSettingStoreImpl(
    isNotificationEnabled: Boolean,
    private val settingStore: SettingProtoStore
) : SetNotificationEnabledSettingStore(isNotificationEnabled) {

    override fun execute(): Flow<Boolean> {
        return settingStore.setNotification(isNotificationEnabled)
            .catch { error ->
                Timber.e(error)
                emit(false)
            }
    }

}

interface GetNotificationEnabledSettingStore : BaseUseCase<Boolean>

class GetNotificationEnabledSettingStoreImpl(
    private val settingStore: SettingProtoStore
) : GetNotificationEnabledSettingStore {

    override fun execute(): Flow<Boolean> {
        return settingStore.enableNotification
    }

}

abstract class SetLoggedInSettingStore(
    protected val isLoggedIn: Boolean
) : BaseUseCase<Boolean>

class SetLoggedInSettingStoreImpl(
    isLoggedIn: Boolean,
    private val settingStore: SettingProtoStore
) : SetLoggedInSettingStore(isLoggedIn) {

    override fun execute(): Flow<Boolean> {
        return settingStore.setLoggedIn(isLoggedIn)
            .catch { error ->
                Timber.e(error)
                emit(false)
            }
    }

}

interface GetLoggedInSettingStore : BaseUseCase<Boolean>

class GetLoggedInSettingStoreImpl(
    private val isLoggedIn: Boolean,
    private val settingStore: SettingProtoStore
) : GetLoggedInSettingStore {

    override fun execute(): Flow<Boolean> {
        return settingStore.isLoggedIn
    }

}

abstract class SetGuestModeSettingStore(
    protected val isGuest: Boolean
) : BaseUseCase<Boolean>

class SetGuestModeSettingStoreImpl(
    isGuest: Boolean,
    private val settingStore: SettingProtoStore
) : SetGuestModeSettingStore(isGuest) {

    override fun execute(): Flow<Boolean> {
        return settingStore.setGuestMode(isGuest)
            .catch { error ->
                Timber.e(error)
                emit(false)
            }
    }

}

interface GetGuestModeSettingStore : BaseUseCase<Boolean>

class GetGuestModeSettingStoreImpl(
    private val isGuest: Boolean,
    private val settingStore: SettingProtoStore
) : GetGuestModeSettingStore {

    override fun execute(): Flow<Boolean> {
        return settingStore.isGuest
    }
}

interface ClearSettingStore : BaseUseCase<Boolean>

class ClearSettingStoreImpl(
    private val settingStore: SettingProtoStore
) : ClearSettingStore {

    override fun execute(): Flow<Boolean> {
        return settingStore.clear()
    }
}