package com.ch8n.thatsmine.domain.models

import com.github.javafaker.Faker
import java.util.*

data class User(
    val userId: String,
    val userName: String,
    val fullName: String,
    val email: String
) {
    companion object {
        fun default() = User(
            userId = "",
            userName = "",
            fullName = "",
            email = "",
        )

        fun mock() = Faker().let { faker ->
            User(
                userId = UUID.randomUUID().toString(),
                userName = faker.name().username(),
                fullName = faker.name().fullName(),
                email = faker.internet().emailAddress()
            )
        }
    }

}