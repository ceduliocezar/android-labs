package com.ceduliocezar.koin.presentation.viewmodel

import android.content.Intent
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ceduliocezar.koin.CustomApplication
import com.ceduliocezar.koin.R
import com.ceduliocezar.koin.domain.HelloRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.*
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules


@RunWith(AndroidJUnit4::class)
class ViewModelActivityTest {

    var mockRepository = mockk<HelloRepository>()

    @Before
    fun setUp() {
        val customApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CustomApplication
        IdlingRegistry.getInstance().register(customApplication.countingIdlingResource)
    }


    @Rule
    @JvmField
    val rule = object : IntentsTestRule<ViewModelActivity>(ViewModelActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()

            val mockModule = module(override = true) {
                single { mockRepository }
            }
            loadKoinModules(listOf(mockModule))
        }
    }

    @Test
    fun test_it_should_show_mocked_text() {

        val fakeText = "Hello from repository mock side!"
        every { mockRepository.giveSlowHello() } returns fakeText

        rule.launchActivity(Intent())

        onView(ViewMatchers.withText(fakeText)).check(matches(isDisplayed()))
    }

    @Test
    fun test_it_should_show_error_message_when_error_occurs() {


        every { mockRepository.giveSlowHello() } throws RuntimeException()

        rule.launchActivity(Intent())

        onView(withId(R.id.error)).check(matches(isDisplayed()))
    }

}