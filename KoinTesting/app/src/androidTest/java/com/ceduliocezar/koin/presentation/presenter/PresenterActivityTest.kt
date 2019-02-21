package com.ceduliocezar.koin.presentation.presenter

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ceduliocezar.koin.CustomApplication
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules

@RunWith(AndroidJUnit4::class)
class PresenterActivityTest {

    var mockPresenter = mockk<SimplePresenter>()

    @Rule
    @JvmField
    val rule = object : IntentsTestRule<PresenterActivity>(PresenterActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()

            val module = module(override = true) {
                factory { mockPresenter }
            }
            loadKoinModules(listOf(module))
        }
    }

    @Before
    fun setUp() {
        val customApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CustomApplication
        IdlingRegistry.getInstance().register(customApplication.countingIdlingResource)
    }

    @Test
    fun test() {

        val mockText = "Hello from the mock side!"

        val slot = slot<PresenterView>()

        every {
            mockPresenter.onCreate(view = capture(slot))
        } answers {
            println("hey: $slot.captured")
            slot.captured.showText(mockText)
        }

        emptyAnswerFor { mockPresenter.onDestroy() }

        rule.launchActivity(Intent())

        onView(withText(mockText)).check(matches(isDisplayed()))
    }

    private fun emptyAnswerFor(execution: () -> Unit) {
        every {
            execution()
        } answers {
            // nothing to do
        }
    }

}
