package com.ceduliocezar.koin.integration

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ceduliocezar.koin.CustomApplication
import com.ceduliocezar.koin.presentation.viewmodel.ViewModelActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ViewModelActivityIntegrationTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ViewModelActivity::class.java)

    @Before
    fun setUp() {
        val customApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CustomApplication
        IdlingRegistry.getInstance().register(customApplication.countingIdlingResource)
    }

    @Test
    fun test() {
        val text = "Hello from HelloRepositoryImpl"

        onView(withText(text)).check(matches(isDisplayed()))
    }

}