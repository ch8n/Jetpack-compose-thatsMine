package com.ch8n.thatsmine.data.repository.ownerItem

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnitRunner
import com.ch8n.thatsmine.data.repository.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OwnerItemRepoImplTest {

    lateinit var repoImpl: OwnerItemRepoImpl

    @Before
    fun setUp() {
        repoImpl = OwnerItemRepoImpl()
    }

    @Test
    fun `get list of owner item returns emptyList`() {
        val items = repoImpl.getOwnedItem(0)
        Truth.assertThat(items).hasSize(0)
    }
}