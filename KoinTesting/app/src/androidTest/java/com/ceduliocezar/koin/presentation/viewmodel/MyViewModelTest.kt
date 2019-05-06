package com.ceduliocezar.koin.presentation.viewmodel

import android.os.Handler
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ceduliocezar.koin.domain.HelloRepository
import com.ceduliocezar.koin.threading.AsyncOperation
import com.ceduliocezar.koin.threading.AsyncProvider
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyViewModelTest {


    private lateinit var subjectUnderTesting: MyViewModel

    @MockK
    lateinit var helloRepository: HelloRepository

    @MockK
    lateinit var asyncProvider: AsyncProvider

    @MockK
    lateinit var asyncOperation: AsyncOperation<String>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun test_init_success() {

        val slot: CapturingSlot<(it: String) -> Unit> = slot()

        every { asyncProvider.create(any<() -> String>()) } returns asyncOperation
        every { asyncOperation.onError(any()) } returns asyncOperation
        every { asyncOperation.onSuccess(capture(slot)) } returns asyncOperation
        every { asyncOperation.execute() } returns Unit

        subjectUnderTesting = MyViewModel(helloRepository, asyncProvider)

        onMainThread {
            slot.captured("success!!")
            assertEquals("success!!", subjectUnderTesting.liveDataString.value)
        }
    }

    @Test
    fun test_init_error() {

        val slot: CapturingSlot<(it: Throwable) -> Unit> = slot()

        every { asyncProvider.create(any<() -> String>()) } returns asyncOperation
        every { asyncOperation.onError(capture(slot)) } returns asyncOperation
        every { asyncOperation.onSuccess(any()) } returns asyncOperation
        every { asyncOperation.execute() } returns Unit

        val exception = mockk<Throwable>()

        subjectUnderTesting = MyViewModel(helloRepository, asyncProvider)

        onMainThread {
            slot.captured(exception)
            assertTrue(subjectUnderTesting.displayGeneralErrorMessage.value!!)
        }
    }


    private fun onMainThread(execution: () -> Unit) {
        Handler(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.mainLooper).post {
            execution()
        }
    }
}