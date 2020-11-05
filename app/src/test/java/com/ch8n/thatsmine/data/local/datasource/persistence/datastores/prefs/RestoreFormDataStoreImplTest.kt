package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs

import androidx.datastore.preferences.clear
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.toMutablePreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs.RestoreFormDataStoreImpl.Companion.ITEM_NAME
import com.ch8n.thatsmine.domain.models.Origin
import com.ch8n.thatsmine.domain.repository.InstantTaskExecutorRule
import com.ch8n.thatsmine.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.prefs.Preferences


@RunWith(AndroidJUnit4::class)
class RestoreFormDataStoreImplTest {

    private lateinit var formDs: RestoreFormDataStoreImpl

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        formDs = spyk(RestoreFormDataStoreImpl(appContext, "pref_test_file_data_store"))
    }

    @After
    fun tearDown() = runBlocking {
        formDs.dataStore.edit { it.clear() }
        Unit
    }

    @Test
    fun `form store item value on update returns same value`() = runBlocking {
        val isSuccess = formDs.setItemName("test").first()
        val value = formDs.itemName.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo("test")
    }

    @Test
    fun `form store item value on update fails should return empty value`() = runBlocking {
        val value = formDs.itemName.first()
        Truth.assertThat(value).isEmpty()
    }

    @Test
    fun `form store ItemDescription on update returns same value`() = runBlocking {
        val isSuccess = formDs.setItemDescription("test").first()
        val value = formDs.itemDescription.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo("test")
    }

    @Test
    fun `form store ItemDescription on update fails should return empty value`() = runBlocking {
        val value = formDs.itemDescription.first()
        Truth.assertThat(value).isEmpty()
    }

    @Test
    fun `form store originName on update returns same value`() = runBlocking {
        val isSuccess = formDs.setOriginName("test").first()
        val value = formDs.originName.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo("test")
    }

    @Test
    fun `form store originName on update fails should return empty value`() = runBlocking {
        val value = formDs.originName.first()
        Truth.assertThat(value).isEmpty()
    }


    @Test
    fun `form store ownedAt on update returns same value`() = runBlocking {
        val isSuccess = formDs.setOwnedAt(12345).first()
        val value = formDs.ownedAt.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo(12345)
    }

    @Test
    fun `form store ownedAt on update fails should return empty value`() = runBlocking {
        val value = formDs.ownedAt.first()
        Truth.assertThat(value).isEqualTo(0)
    }

    @Test
    fun `form store expiresIn on update returns same value`() = runBlocking {
        val isSuccess = formDs.setExpiresIn(12345).first()
        val value = formDs.expiresIn.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo(12345)
    }

    @Test
    fun `form store expiresIn on update fails should return empty value`() = runBlocking {
        val value = formDs.expiresIn.first()
        Truth.assertThat(value).isEqualTo(0)
    }


    @Test
    fun `form store origin on update returns same value`() = runBlocking {
        val isSuccess = formDs.setOrigin(Origin.GIFTED).first()
        val value = formDs.origin.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isInstanceOf(Origin.GIFTED::class.java)
    }

    @Test
    fun `form store origin on update fails should return empty value`() = runBlocking {
        val value = formDs.origin.first()
        Truth.assertThat(value).isInstanceOf(Origin.UNDEFINED::class.java)
    }

    @Test
    fun `form store inventory on update returns same value`() = runBlocking {
        val isSuccess = formDs.setInventory(10).first()
        val value = formDs.inventory.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo(10)
    }

    @Test
    fun `form store inventory on update fails should return empty value`() = runBlocking {
        val value = formDs.inventory.first()
        Truth.assertThat(value).isEqualTo(0)
    }

    @Test
    fun `form store isFavourite on update returns same value`() = runBlocking {
        val isSuccess = formDs.setFavourite(true).first()
        val value = formDs.isFavourite.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isTrue()
    }

    @Test
    fun `form store isFavourite on update fails should return empty value`() = runBlocking {
        val value = formDs.isFavourite.first()
        Truth.assertThat(value).isFalse()
    }


}