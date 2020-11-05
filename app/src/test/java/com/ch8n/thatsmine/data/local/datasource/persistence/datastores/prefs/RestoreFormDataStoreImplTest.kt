package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ch8n.thatsmine.domain.repository.InstantTaskExecutorRule
import com.ch8n.thatsmine.utils.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.prefs.Preferences


@RunWith(AndroidJUnit4::class)
class RestoreFormDataStoreImplTest {

    private lateinit var formDs: RestoreFormDataStoreImpl

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        formDs = spyk(RestoreFormDataStoreImpl(appContext, "pref_test_file_data_store"))
    }

    @Test
    fun `form store item value on update returns same value`() = runBlocking {
        val isSuccess = formDs.setItemName("test").first()
        val value = formDs.itemName.first()
        Truth.assertThat(isSuccess).isTrue()
        Truth.assertThat(value).isEqualTo("test")
    }

    @Test
    fun `form store item value on update fails should return empty value`() = runBlocking {
        val isSuccess = formDs.setItemName("test").first()
        Truth.assertThat(isSuccess).isTrue()

        val value = formDs.itemName.map { throw NullPointerException() }

        Truth.assertThat(value).isEqualTo("")
    }


}