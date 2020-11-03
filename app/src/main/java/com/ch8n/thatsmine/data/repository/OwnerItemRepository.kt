package com.ch8n.thatsmine.data.repository

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.data.local.models.OwnerItem

interface OwnerItemRepository {
    fun addOwnerItem(item: OwnerItem)
    fun getOwnerItems(): List<OwnerItem>
}

class OwnerItemRepoImpl(private val memoryDataStore: MemoryDataStore) : OwnerItemRepository {

    override fun addOwnerItem(item: OwnerItem) {
        memoryDataStore.addOwnerItem(item)
    }

    override fun getOwnerItems(): List<OwnerItem> {
        return memoryDataStore.getOwnerItems()
    }

}