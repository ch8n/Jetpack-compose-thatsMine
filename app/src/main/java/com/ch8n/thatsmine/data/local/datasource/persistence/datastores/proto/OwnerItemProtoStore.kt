package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import android.content.Context
import com.ch8n.thatsmine.ProtoOwnedItem
import com.ch8n.thatsmine.ProtoOwnedItems
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PROTO_OWNED_ITEM_FILE_NAME
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.ProtoDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.domain.models.Origin
import com.ch8n.thatsmine.domain.models.OwnerItem
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.*

interface OwnedItemProtoStore {
    val lastViewedItem: Flow<OwnerItem>
    val bufferItems: Flow<List<OwnerItem>>

    fun clear(): Flow<Boolean>
    fun setLastViewedItem(lastViewedItem: OwnerItem): Flow<Boolean>
    fun setBufferList(bufferItems: List<OwnerItem>): Flow<Boolean>
}

class OwnedItemProtoStoreImpl(
    context: Context,
    config: StoreConfig<ProtoOwnedItems> = StoreConfig(
        fileName = PROTO_OWNED_ITEM_FILE_NAME,
        SerializersOwnedItem
    )
) : OwnedItemProtoStore, ProtoDataStore<ProtoOwnedItems>(context.applicationContext, config) {

    private fun ProtoOwnedItem.toOwnerItems(): OwnerItem {
        return OwnerItem(
            itemId = this.itemId,
            itemName = this.itemName,
            itemDescription = this.itemDescription,
            ownedAt = this.ownedAt,
            inventory = this.inventory,
            expiresIn = this.expiresIn,
            imageUrl = this.imageUrl,
            origin = when (this.origin) {
                ProtoOwnedItem.Origin.Gifted -> Origin.GIFTED
                ProtoOwnedItem.Origin.Purchased -> Origin.PURCHASED
                ProtoOwnedItem.Origin.UNRECOGNIZED -> Origin.UNDEFINED
                ProtoOwnedItem.Origin.Unknown -> Origin.UNDEFINED
            },
            originName = this.originName,
            isFavourite = this.isFavourite
        )
    }

    private fun OwnerItem.toProtoOwnedItem(): ProtoOwnedItem {
        return ProtoOwnedItem.newBuilder()
            .setItemId(this.itemId)
            .setItemName(this.itemName)
            .setItemDescription(this.itemDescription)
            .setOwnedAt(this.ownedAt)
            .setInventory(this.inventory)
            .setExpiresIn(this.expiresIn)
            .setImageUrl(this.imageUrl)
            .setOrigin(
                when (this.origin) {
                    Origin.UNDEFINED -> ProtoOwnedItem.Origin.Unknown
                    Origin.GIFTED -> ProtoOwnedItem.Origin.Gifted
                    Origin.PURCHASED -> ProtoOwnedItem.Origin.Purchased
                }
            )
            .setOriginName(this.originName)
            .setIsFavourite(this.isFavourite)
            .build()
    }

    override val lastViewedItem: Flow<OwnerItem>
        get() = dataStore.data.map { store ->
            val item = store.lastViewItem.toOwnerItems()
            if (item.itemId.isEmpty()) {
                item.copy(itemId = UUID.randomUUID().toString())
            } else {
                item
            }
        }


    override val bufferItems: Flow<List<OwnerItem>>
        get() = dataStore.data.map { store ->
            store.offlineBufferItemList.map { it.toOwnerItems() }
        }


    override fun clear(): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.updateData { store ->
                store.toBuilder().clear().build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setLastViewedItem(lastViewedItem: OwnerItem): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.updateData { store ->
                val storeItem = lastViewedItem.toProtoOwnedItem()
                store.toBuilder()
                    .clearLastViewItem()
                    .setLastViewItem(storeItem)
                    .build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }

    override fun setBufferList(bufferItems: List<OwnerItem>): Flow<Boolean> {
        return flow<Boolean> {
            dataStore.updateData { store ->
                val storeItem = bufferItems.map { it.toProtoOwnedItem() }
                store.toBuilder()
                    .clearOfflineBufferItem()
                    .addAllOfflineBufferItem(storeItem)
                    .build()
            }
            emit(true)
        }.catch { error ->
            Timber.e(error)
            emit(false)
        }
    }
}

