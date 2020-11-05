package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import androidx.datastore.preferences.clear
import androidx.datastore.preferences.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs.RestoreFormDataStoreImpl
import com.ch8n.thatsmine.domain.models.Origin
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.ch8n.thatsmine.domain.repository.InstantTaskExecutorRule
import com.ch8n.thatsmine.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnedItemProtoStoreImplTest {

    private lateinit var itemStored: OwnedItemProtoStoreImpl

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        itemStored = spyk(
            OwnedItemProtoStoreImpl(
                appContext, StoreConfig(
                    fileName = "proto_test_file_data_store",
                    serializer = SerializersOwnedItem
                )
            )
        )
    }

    @After
    fun tearDown() = runBlocking {
        itemStored.dataStore.updateData {
            it.toBuilder().clear().build()
        }
        Unit
    }


    @Test
    fun `on last Viewed Item update returns same value`() = runBlocking {
        val mockItem = OwnerItem.mock()
        val isSuccess = itemStored.setLastViewedItem(mockItem).first()
        val value = itemStored.lastViewedItem.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value.itemId).isEqualTo(mockItem.itemId)
    }

    @Test
    fun `on last Viewed Item fails returns OwnedItem empty object`() = runBlocking {
        val value = itemStored.lastViewedItem.first()
        Truth.assertThat(value.itemId).isNotEmpty()
        Truth.assertThat(value.itemDescription).isEmpty()
        Truth.assertThat(value.origin).isInstanceOf(Origin.UNDEFINED::class.java)
        Truth.assertThat(value.imageUrl).isEmpty()
        Truth.assertThat(value.ownedAt).isEqualTo(0)
    }

    @Test
    fun `on buffer owned item list update returns same value`() = runBlocking {
        val mockItems = listOf(OwnerItem.mock(),OwnerItem.mock(),OwnerItem.mock())
        val isSuccess = itemStored.setBufferList(mockItems).first()
        val value = itemStored.bufferItems.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).hasSize(3)
        Truth.assertThat(value.get(0)).isEqualTo(mockItems.get(0))
        Truth.assertThat(value.get(1)).isEqualTo(mockItems.get(1))
        Truth.assertThat(value.get(2)).isEqualTo(mockItems.get(2))
    }

    @Test
    fun `on buffer owned item list fails returns emptyList`() = runBlocking {
        val value = itemStored.bufferItems.first()
        Truth.assertThat(value).isEmpty()
    }


}