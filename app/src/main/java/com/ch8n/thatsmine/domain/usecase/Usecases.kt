package com.ch8n.thatsmine.domain.usecase

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore

class GetMemoryOwnedItems(private val memoryDataStore: MemoryDataStore)