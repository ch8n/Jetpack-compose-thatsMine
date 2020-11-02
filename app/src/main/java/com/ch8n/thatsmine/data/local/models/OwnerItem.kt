package com.ch8n.thatsmine.data.local.models

import androidx.compose.runtime.Immutable

@Immutable
data class OwnerItem(
    val itemName: String = "",
    val itemDescription: String = "",
    val ownedAt: Long = 0,
    val inventory: Int = 0,
    val expiresIn: Long = 0,
    val imageUrl: String = "",
    val origin: Origin = Origin.UNDEFINED,
    val originName: String = "",
    val isFavourite: Boolean = false
)

enum class Origin {
    UNDEFINED,
    GIFTED,
    PURCHASED
}