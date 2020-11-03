package com.ch8n.thatsmine.domain.usecase

import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.models.OwnerItem
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddOwnedItemInMemoryImplTest {

    private lateinit var usecase: AddOwnedItemInMemory
    private lateinit var dataSource: MemoryDataStore

    @Before
    fun setUp() {
        dataSource = mockk(relaxed = true)
        usecase = AddOwnedItemInMemoryImpl(OwnerItem.mock(), dataSource)
    }

    @Test
    fun `add owned item is success`() = runBlocking {
        //given
        every { dataSource.addOwnerItem(any()) } returns Unit
        //act
        val isSuccess = usecase.execute().singleOrNull()
        //assert
        verify(exactly = 1) { dataSource.addOwnerItem(any()) }
        Truth.assertThat(isSuccess).isNotNull()
        Truth.assertThat(isSuccess).isTrue()
    }

    @Test
    fun `get owned throws some exception`() = runBlocking {
        //given
        every { dataSource.addOwnerItem(any()) } throws NullPointerException("test")
        //act
        val isSuccess = usecase.execute().singleOrNull()
        //assert
        verify(exactly = 1) { dataSource.addOwnerItem(any()) }
        Truth.assertThat(isSuccess).isNotNull()
        Truth.assertThat(isSuccess).isFalse()
    }



}