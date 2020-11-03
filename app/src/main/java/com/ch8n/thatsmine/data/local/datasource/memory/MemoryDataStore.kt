package com.ch8n.thatsmine.data.local.datasource.memory

import android.util.LruCache
import com.ch8n.thatsmine.domain.models.OwnerItem

interface MemoryDataStore {
    fun addOwnerItem(item: OwnerItem)
    fun getOwnerItems(): List<OwnerItem>
}

class MemoryDataStoreImpl : MemoryDataStore {

    val memoryDataStore = LruCache</*OwnerItemId*/String, OwnerItem>(5)

    override fun addOwnerItem(item: OwnerItem) {
        memoryDataStore.put(item.itemId, item)
    }

    override fun getOwnerItems(): List<OwnerItem> {
        return memoryDataStore.snapshot().values.toList()
    }

}

class FakeMemoryDataStoreImpl : MemoryDataStore {

    private val mockMemoryData = mutableListOf<OwnerItem>()

    override fun addOwnerItem(item: OwnerItem) {
        // not supported
    }

    override fun getOwnerItems(): List<OwnerItem> {
        mockMemoryData.clear()
        repeat(5) {
            mockMemoryData.add(OwnerItem.mock())
        }
        return mockMemoryData
    }

}