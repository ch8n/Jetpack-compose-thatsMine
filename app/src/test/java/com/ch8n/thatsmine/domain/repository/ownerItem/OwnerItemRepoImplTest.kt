package com.ch8n.thatsmine.domain.repository.ownerItem

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ch8n.thatsmine.data.local.datasource.memory.FakeMemoryDataStoreImpl
import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.ch8n.thatsmine.domain.repository.OwnerItemRepoImpl
import com.ch8n.thatsmine.domain.usecase.AddOwnedItemInMemory
import com.ch8n.thatsmine.domain.usecase.GetOwnedItemsFromMemory
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import kotlin.NullPointerException

@RunWith(AndroidJUnit4::class)
class OwnerItemRepoImplTest {

    private lateinit var repoImpl: OwnerItemRepoImpl
    private lateinit var getOwnedItemsFromMemory: GetOwnedItemsFromMemory
    private lateinit var addOwnedItemInMemory: AddOwnedItemInMemory

    @Before
    fun setUp() {
        getOwnedItemsFromMemory = mockk(relaxed = true)
        addOwnedItemInMemory = mockk(relaxed = true)
        repoImpl = OwnerItemRepoImpl(getOwnedItemsFromMemory, addOwnedItemInMemory)
    }

    @Test
    fun `get list of owner item returns 3 items`() = runBlocking {
        every { getOwnedItemsFromMemory.execute() } returns flow {
            this.emit(
                listOf(
                    OwnerItem.mock(),
                    OwnerItem.mock(),
                    OwnerItem.mock(),
                )
            )
        }
        val items = repoImpl.getOwnerItems()
        verify(exactly = 1) { getOwnedItemsFromMemory.execute() }
        Truth.assertThat(items).hasSize(3)
    }

    @Test
    fun `get list onFail return empty`() = runBlocking {
        every { getOwnedItemsFromMemory.execute() } throws NullPointerException("test")
        val items = repoImpl.getOwnerItems()
        verify(exactly = 1) { getOwnedItemsFromMemory.execute() }
        Truth.assertThat(items).isEmpty()
    }

    @Test
    fun `add list item success`() = runBlocking {
        every { addOwnedItemInMemory.execute() } returns flow { emit(true) }
        val result = repoImpl.addOwnerItem(OwnerItem.mock())
        verify(exactly = 1) { addOwnedItemInMemory.execute() }
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `add list item operation fails`() = runBlocking {
        every { addOwnedItemInMemory.execute() } returns flow { emit(false) }
        val result = repoImpl.addOwnerItem(OwnerItem.mock())
        verify(exactly = 1) { addOwnedItemInMemory.execute() }
        Truth.assertThat(result).isFalse()
    }


    @Test
    fun `add list item operation fails due to null pointer`() = runBlocking {
        every { addOwnedItemInMemory.execute() } throws NullPointerException("test")
        val result = repoImpl.addOwnerItem(OwnerItem.mock())
        verify(exactly = 1) { addOwnedItemInMemory.execute() }
        Truth.assertThat(result).isFalse()
    }

}