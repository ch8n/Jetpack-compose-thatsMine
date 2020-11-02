package com.ch8n.thatsmine.data.repository.ownerItem

import com.ch8n.thatsmine.data.local.models.OwnerItem

interface OwnerItemRepository {
    fun getOwnedItem(size: Int): List<OwnerItem>
}

class OwnerItemRepoImpl : OwnerItemRepository {

    override fun getOwnedItem(size: Int): List<OwnerItem> {
        val sampleList = mutableListOf<OwnerItem>()
        repeat(size) {
            sampleList.add(OwnerItem.mock())
        }
        return sampleList
    }

}