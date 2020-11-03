package com.ch8n.thatsmine.domain.usecase

import com.ch8n.thatsmine.base.usecase.BaseUseCase
import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber


interface GetOwnedItemsFromMemory : BaseUseCase<List<OwnerItem>>

class GetOwnedItemsFromMemoryImpl(
    private val memoryDataStore: MemoryDataStore
) : GetOwnedItemsFromMemory {

    override fun execute(): Flow<List<OwnerItem>> {
        return flow {
            Timber.d("started flow GetMemoryOwnedItems")
            val ownedItems = memoryDataStore.getOwnerItems()
            Timber.d("emitting flow GetMemoryOwnedItems")
            emit(ownedItems)
        }.catch { emit(emptyList()) }
    }
}


interface AddOwnedItemInMemory : BaseUseCase<Boolean>

class AddOwnedItemInMemoryImpl(
    private val item: OwnerItem,
    private val memoryDataStore: MemoryDataStore
) : AddOwnedItemInMemory {

    override fun execute(): Flow<Boolean> {
        return flow {
            Timber.d("started flow AddOwnedItemInMemory")
            memoryDataStore.addOwnerItem(item)
            Timber.d("emitting flow AddOwnedItemInMemory")
            emit(true)
        }.catch { emit(false) }
    }
}