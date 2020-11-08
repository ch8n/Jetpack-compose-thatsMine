package com.ch8n.thatsmine.domain.usecase


import com.ch8n.thatsmine.base.usecase.BaseUseCase
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto.OwnedItemProtoStore
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto.SettingProtoStore
import com.ch8n.thatsmine.domain.models.AppTheme
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.ch8n.thatsmine.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber


abstract class SetLastViewedItemOwnerStore(
    protected val lastViewedItem: OwnerItem
) : BaseUseCase<Boolean>

class SetLastViewedItemOwnerStoreImpl(
    lastViewedItem: OwnerItem,
    private val store: OwnedItemProtoStore
) : SetLastViewedItemOwnerStore(lastViewedItem) {

    override fun execute(): Flow<Boolean> {
        return store.setLastViewedItem(lastViewedItem)
    }

}

interface GetLastViewedItemOwnerStore : BaseUseCase<OwnerItem>

class GetLastViewedItemOwnerStoreImpl(
    private val store: OwnedItemProtoStore
) : GetLastViewedItemOwnerStore {

    override fun execute(): Flow<OwnerItem> {
        return store.lastViewedItem
    }

}


abstract class SetBufferItemsOwnerStore(
    protected val bufferItems: List<OwnerItem>
) : BaseUseCase<Boolean>

class SetBufferItemsOwnerStoreImpl(
    bufferItems: List<OwnerItem>,
    private val store: OwnedItemProtoStore
) : SetBufferItemsOwnerStore(bufferItems) {

    override fun execute(): Flow<Boolean> {
        return store.setBufferList(bufferItems)
    }

}

interface GetBufferItemsOwnerStore : BaseUseCase<List<OwnerItem>>

class GetBufferItemsOwnerStoreImpl(
    private val store: OwnedItemProtoStore
) : GetBufferItemsOwnerStore {

    override fun execute(): Flow<List<OwnerItem>> {
        return store.bufferItems
    }

}

interface ClearItemStore : BaseUseCase<Boolean>

class ClearItemStoreImpl(
    private val store: OwnedItemProtoStore
) : ClearItemStore {

    override fun execute(): Flow<Boolean> {
        return store.clear()
    }
}