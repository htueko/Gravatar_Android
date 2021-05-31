package com.htueko.gravatarandroid.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.htueko.gravatarandroid.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class MainActivityTest {

    // https://developer.android.com/training/dependency-injection/hilt-testing#multiple-testrules
    // https://stackoverflow.com/a/63367218/9056898
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_is_activity_displayed() {
        // check the MainActivity is displayed
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_of_fragment_container() {
        // check the Fragment container is displayed
        onView(withId(R.id.nav_host_fragment))
            .check(matches(isDisplayed()))
    }


}