package com.ch8n.thatsmine.data.repository.ownerItem

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ch8n.thatsmine.data.local.datasource.memory.FakeMemoryDataStoreImpl
import com.ch8n.thatsmine.data.repository.OwnerItemRepoImpl
import com.google.common.truth.Truth
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnerItemRepoImplTest {

    private lateinit var repoImpl: OwnerItemRepoImpl

    @Before
    fun setUp() {
        val fakeMemoryStore = FakeMemoryDataStoreImpl()
        repoImpl = OwnerItemRepoImpl(fakeMemoryStore)
    }

    @Test
    fun `get list of owner item returns 5 items`() {
        val items = repoImpl.getOwnerItems()
        Truth.assertThat(items).hasSize(5)
    }
}