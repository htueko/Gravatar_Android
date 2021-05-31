package com.htueko.gravatarandroid.ui.fragment

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.htueko.gravatarandroid.R
import com.htueko.gravatarandroid.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration test for the dashboard screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class DashboardFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun test_views_is_displayed() {
        // launch the Dashboard Fragment
        launchFragmentInHiltContainer<DashboardFragment>(Bundle(), R.style.Theme_GravatarAndroid) {}
        // check the Dashboard  Fragment is displayed
        onView(withId(R.id.fragment_dashboard))
            .check(matches(isDisplayed()))
        // check the toolbar is displayed
        onView(withId(R.id.toolbar))
            .check(matches(isDisplayed()))
        // check the recycler view is displayed
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))
        // check the random button is displayed
        onView(withId(R.id.btn_random))
            .check(matches(isDisplayed()))
    }

}