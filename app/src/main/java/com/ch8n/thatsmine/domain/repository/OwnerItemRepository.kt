package com.ch8n.thatsmine.domain.repository

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem

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