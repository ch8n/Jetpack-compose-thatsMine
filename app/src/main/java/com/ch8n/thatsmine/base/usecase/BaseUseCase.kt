package com.ch8n.thatsmine.base.usecase

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T> {
    fun execute(): Flow<T>
}