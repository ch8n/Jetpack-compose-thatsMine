package com.ch8n.thatsmine.domain.repository.ownerItem

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ch8n.thatsmine.data.local.datasource.memory.FakeMemoryDataStoreImpl
import com.ch8n.thatsmine.data.local.datasource.memory.MemoryDataStore
import com.ch8n.thatsmine.domain.repository.OwnerItemRepoImpl
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnerItemRepoImplTest {

    private lateinit var mockMemoryStore: MemoryDataStore
    private lateinit var repoImpl: OwnerItemRepoImpl
    private lateinit var fakeDataStore: FakeMemoryDataStoreImpl

    @Before
    fun setUp() {
        mockMemoryStore = mockk<MemoryDataStore>()
        repoImpl = OwnerItemRepoImpl(mockMemoryStore)
        fakeDataStore = FakeMemoryDataStoreImpl()
    }

    @Test
    fun `get list of owner item returns 5 items`() {
        every { mockMemoryStore.getOwnerItems() } returns fakeDataStore.getOwnerItems()
        val items = repoImpl.getOwnerItems()
        Truth.assertThat(items).hasSize(5)
    }


}