package com.ch8n.thatsmine.domain.repository

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.ch8n.thatsmine.domain.usecase.AddOwnedItemInMemory
import com.ch8n.thatsmine.domain.usecase.GetOwnedItemsFromMemory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext

interface OwnerItemRepository {
    suspend fun addOwnerItem(item: OwnerItem): Boolean
    suspend fun getOwnerItems(): List<OwnerItem>
}

class OwnerItemRepoImpl(
    private val getOwnedItemsFromMemory: GetOwnedItemsFromMemory,
    private val addOwnedItemInMemory: AddOwnedItemInMemory
) : OwnerItemRepository {

    override suspend fun addOwnerItem(item: OwnerItem): Boolean {
        val result = kotlin.runCatching { addOwnedItemInMemory.execute() }
            .getOrDefault(flowOf(false))
            .catch { emit(false) }
            .singleOrNull()
        return result == true
    }

    override suspend fun getOwnerItems(): List<OwnerItem> {
        val result = kotlin.runCatching { getOwnedItemsFromMemory.execute() }
            .getOrDefault(flowOf(emptyList()))
            .catch { emit(emptyList()) }
            .singleOrNull()
        return result ?: emptyList()
    }

}