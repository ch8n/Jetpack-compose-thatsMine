package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.proto

import android.content.Context
import com.ch8n.thatsmine.ProtoOwnedItem
import com.ch8n.thatsmine.ProtoOwnedItems
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.PROTO_OWNED_ITEM_FILE_NAME
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.ProtoDataStore
import com.ch8n.thatsmine.data.local.datasource.persistence.datastores.config.StoreConfig
import com.ch8n.thatsmine.domain.models.Origin
import com.ch8n.thatsmine.domain.models.OwnerItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface OwnedItemProtoStore {
    var lastViewedItem: Flow<OwnerItem>
    var bufferItems: Flow<List<OwnerItem>>
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
            .setImageUrl(this.itemId)
            .setOrigin(
                when (this.origin) {
                    Origin.UNDEFINED -> ProtoOwnedItem.Origin.UNRECOGNIZED
                    Origin.GIFTED -> ProtoOwnedItem.Origin.Gifted
                    Origin.PURCHASED -> ProtoOwnedItem.Origin.Purchased
                }
            )
            .setOriginName(this.originName)
            .setIsFavourite(this.isFavourite)
            .build()
    }

    override var lastViewedItem: Flow<OwnerItem>
        get() = dataStore.data.map { store ->
            store.lastViewItem.toOwnerItems()
        }
        set(value) {
            value.map { item ->
                dataStore.updateData { store ->
                    val storeItem = item.toProtoOwnedItem()
                    store.toBuilder().setLastViewItem(storeItem).build()
                }
            }
        }

    override var bufferItems: Flow<List<OwnerItem>>
        get() = dataStore.data.map { store ->
            store.offlineBufferItemList.map { it.toOwnerItems() }
        }
        set(value) {
            value.map { item ->
                item.map { it.toProtoOwnedItem() }
                    .forEachIndexed { index, protoOwnedItem ->
                        dataStore.updateData { store ->
                            store.toBuilder().setOfflineBufferItem(index, protoOwnedItem).build()
                        }
                    }
            }
        }
}

