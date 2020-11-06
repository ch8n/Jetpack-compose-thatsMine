package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.User
import com.ch8n.thatsmine.domain.repository.InstantTaskExecutorRule
import com.ch8n.thatsmine.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsProtoStoreImplTest {

    private lateinit var settingStore: SettingsProtoStoreImpl

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        settingStore = spyk(
            SettingsProtoStoreImpl(
                appContext, StoreConfig(
                    fileName = "proto_test_file_data_store",
                    serializer = SerializersSettings
                )
            )
        )
    }

    @After
    fun tearDown() = runBlocking {
        settingStore.dataStore.updateData {
            it.toBuilder().clear().build()
        }
        Unit
    }


    @Test
    fun `on update of current theme we get updated value`() = runBlocking {
        val isSuccess = settingStore.setCurrentTheme(AppTheme.Dark).first()
        val theme = settingStore.currentTheme.first()

        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(theme).isInstanceOf(AppTheme.Dark::class.java)
    }

    @Test
    fun `on current theme fails get Light theme`() = runBlocking {
        val theme = settingStore.currentTheme.first()
        Truth.assertThat(theme).isInstanceOf(AppTheme.Light::class.java)
    }

    @Test
    fun `on update of active user we get updated value`() = runBlocking {
        val mockUser = User.mock()
        val isSuccess = settingStore.setActiveUser(mockUser).first()
        val value = settingStore.activeUser.first()

        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo(mockUser)
    }

    @Test
    fun `on active user fails get empty object`() = runBlocking {
        val value = settingStore.activeUser.first()
        Truth.assertThat(value.userId).isEmpty()
        Truth.assertThat(value.userName).isEmpty()
        Truth.assertThat(value.email).isEmpty()
    }


}