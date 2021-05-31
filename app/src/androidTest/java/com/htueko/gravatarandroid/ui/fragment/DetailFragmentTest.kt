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
 * Integration test for the detail screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class DetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Populate @Inject fields in test class
        hiltRule.inject()
    }

    @Test
    fun test_views_is_displayed() {
        // launch the Detail Fragment
        launchFragmentInHiltContainer<DetailFragment>(Bundle(), R.style.Theme_GravatarAndroid) {}
        // check the Detail Fragment is displayed
        onView(withId(R.id.fragment_detail))
            .check(matches(isDisplayed()))
        // check the Avatar ImageView is displayed
        onView(withId(R.id.imv_avatar_detail))
            .check(matches(isDisplayed()))
        // check the Name text view is displayed
        onView(withId(R.id.tv_name_detail))
            .check(matches(isDisplayed()))
        // check the Status text view is displayed
        onView(withId(R.id.tv_status_detail))
            .check(matches(isDisplayed()))
        // check the Email text view is displayed
        onView(withId(R.id.tv_email_detail))
            .check(matches(isDisplayed()))

    }

}