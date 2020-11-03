package com.ch8n.thatsmine.domain.usecase

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOwnedItemsFromMemoryImplTest {

    private lateinit var usecase: GetOwnedItemsFromMemory
    private lateinit var dataSource: MemoryDataStore

    @Before
    fun setUp() {
        dataSource = mockk(relaxed = true)
        usecase = GetOwnedItemsFromMemoryImpl(dataSource)
    }

    @Test
    fun `get owned from source return empty list`() = runBlocking {
        //given
        every { dataSource.getOwnerItems() } returns emptyList()
        //act
        val items = usecase.execute()
            .singleOrNull()
        verify(exactly = 1) { dataSource.getOwnerItems() }
        Truth.assertThat(items).isNotNull()
        Truth.assertThat(items).isEmpty()
    }

    @Test
    fun `get owned  from source return null`() = runBlocking {
        //given
        every { dataSource.getOwnerItems() } throws NullPointerException("test")
        //act
        val items = usecase.execute()
            .singleOrNull()
        //assert
        verify(exactly = 1) { dataSource.getOwnerItems() }
        Truth.assertThat(items).isNotNull()
        Truth.assertThat(items).isEmpty()
    }

    @Test
    fun `get owned from source returns 3 items`() = runBlocking {
        //given
        every { dataSource.getOwnerItems() } returns listOf(
            OwnerItem.mock(),
            OwnerItem.mock(),
            OwnerItem.mock()
        )
        //act
        val items = usecase.execute()
            .singleOrNull()
        //assert
        verify(exactly = 1) { dataSource.getOwnerItems() }
        Truth.assertThat(items).isNotNull()
        Truth.assertThat(items).isNotEmpty()
        Truth.assertThat(items?.size).isEqualTo(3)
    }




}