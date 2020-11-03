package com.ch8n.thatsmine.data.local.datasource.memory

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MemoryDataStoreImplTest {

    private lateinit var memoryStore: MemoryDataStoreImpl

    @Before
    fun setUp() {
        memoryStore = MemoryDataStoreImpl()
    }

    @Test
    fun `get list of owner item returns emptyList`() {
        val items = memoryStore.getOwnerItems()
        Truth.assertThat(items).hasSize(0)
    }

    @Test
    fun `get list of owner item even adding 10 items returns 5 items`() {
        repeat(10) {
            memoryStore.addOwnerItem(OwnerItem.mock())
        }
        val items = memoryStore.getOwnerItems()
        Truth.assertThat(items).hasSize(5)
    }

}