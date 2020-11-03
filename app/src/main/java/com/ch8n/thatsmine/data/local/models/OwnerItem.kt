package com.ch8n.thatsmine.data.local.models

import androidx.compose.runtime.Immutable
import com.github.javafaker.Faker
import java.util.*


@Immutable
data class OwnerItem(
    val itemId: String,
    val itemName: String,
    val itemDescription: String,
    val ownedAt: Long,
    val inventory: Int,
    val expiresIn: Long,
    val imageUrl: String,
    val origin: Origin,
    val originName: String,
    val isFavourite: Boolean
) {
    companion object {

        fun default() = OwnerItem(
            itemId = UUID.randomUUID().toString(),
            itemName = "",
            itemDescription = "",
            ownedAt = 0,
            inventory = 0,
            expiresIn = 0,
            imageUrl = "",
            origin = Origin.UNDEFINED,
            originName = "",
            isFavourite = false
        )

        fun mock(): OwnerItem = Faker().let { faker ->
            OwnerItem(
                itemId = UUID.randomUUID().toString(),
                itemName = faker.commerce().productName(),
                itemDescription = faker.friends().quote(),
                ownedAt = faker.date().birthday().time,
                inventory = faker.number().randomDigit(),
                expiresIn = faker.date().birthday().time,
                imageUrl = faker.avatar().image(),
                origin = faker.random().nextInt(1000).let { number ->
                    if (number % 2 == 0) {
                        Origin.GIFTED
                    } else {
                        Origin.PURCHASED
                    }
                },
                originName = faker.name().fullName(),
                isFavourite = faker.random().nextInt(1000) % 2 == 0
            )
        }
    }
}

enum class Origin {
    UNDEFINED,
    GIFTED,
    PURCHASED
}