package com.htueko.gravatarandroid.ui.fragment

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DashboardFragmentTest::class,
    DetailFragmentTest::class
)
class FragmentTestSuite