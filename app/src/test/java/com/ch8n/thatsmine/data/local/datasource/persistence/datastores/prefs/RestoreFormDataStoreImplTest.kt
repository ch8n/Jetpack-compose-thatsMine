package com.ch8n.thatsmine.data.local.datasource.persistence.datastores.prefs

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ch8n.thatsmine.domain.repository.InstantTaskExecutorRule
import com.ch8n.thatsmine.utils.MainCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestoreFormDataStoreImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testPrefsFileName = "testPrefs"
    private lateinit var formDS: RestoreFormDataStoreImpl

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        formDS = RestoreFormDataStoreImpl(appContext, testPrefsFileName)
    }

    @Test
    fun `clearing data from perfs success`() = runBlocking {
        formDS.itemName = flowOf("test")
        val itemCurrentName = formDS.itemName.singleOrNull()
        Truth.assertThat(itemCurrentName).isEqualTo("test")
        val isSuccess = formDS.clear().singleOrNull()
        Truth.assertThat(isSuccess).isTrue()
        val clearedValue = formDS.itemName.singleOrNull()
        Truth.assertThat(itemCurrentName).isEqualTo("")
    }


}