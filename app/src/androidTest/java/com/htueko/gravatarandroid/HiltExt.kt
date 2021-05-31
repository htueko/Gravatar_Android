package com.htueko.gravatarandroid

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider

/**
 * launchFragmentInContainer from the androidx.fragment:fragment-testing library
 * is NOT possible to use right now as it uses a hardcoded Activity under the hood
 * (i.e. [EmptyFragmentActivity]) which is not annotated with @AndroidEntryPoint.
 *
 * As a workaround, use this function that is equivalent. It requires you to add
 * [HiltTestActivity] in the debug folder and include it in the debug AndroidManifest.xml file
 * as can be found in this project.
 */
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    // to access parameter
    fragmentFactory: FragmentFactory? = null,
    crossinline action: Fragment.() -> Unit = {}
) {
    /**
     * intent to launch the Activity with Context and Custom Hilt Test Activity [HiltTestActivity]
     */
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId
    )
    /**
     * launch the Activity Scenario with mainActivityIntent
     */
    ActivityScenario.launch<HiltTestActivity>(startActivityIntent)
        .onActivity { activity ->
            // attach the Fragment Factory to Launched Activity
            fragmentFactory?.let {
                activity.supportFragmentManager.fragmentFactory = it
            }
            // create Fragment instance to launch
            val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
                Preconditions.checkNotNull(T::class.java.classLoader),
                T::class.java.name
            )
            // set the args to Fragment
            fragment.arguments = fragmentArgs
            // launch the Fragment
            activity.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment, "")
                .commitNow()

            fragment.action()
        }
}