package com.feragusper.theforklite.ui

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.feragusper.theforklite.R
import com.feragusper.theforklite.common.ViewShownIdlingResource
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Unit tests for the implementation of [RestaurantDetailFragment].
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun initial_state_home_screen_UI_test() {
        waitViewShown(withId(R.id.restaurantPrice))
        // Sleep (3" as maximum tolerance to load the view) until the data is loaded.
        // This is not the best convenience way to do it, since 3" per each E2E test doesn't scale. Is too expensive.
        // There are a few other ways using idling resources, but I couldn't find anything useful yet.
        Thread.sleep(3000)
        // First visible Part
        onView(withId(R.id.restaurantSpeciality)).check(matches(isDisplayed()))
        onView(withId(R.id.restaurantRate)).check(matches(isDisplayed()))
        onView(withId(R.id.restaurantRateOver)).check(matches(isDisplayed()))
        onView(withId(R.id.restaurantPrice)).check(matches(isDisplayed()))
        onView(withId(R.id.restaurantRates)).check(matches(isDisplayed()))
        onView(withId(R.id.bookButton)).check(matches(isDisplayed()))
    }

    private fun waitViewShown(matcher: Matcher<View>) {
        val idlingResource: IdlingResource = ViewShownIdlingResource(matcher) ///
        try {
            IdlingRegistry.getInstance().register(idlingResource)
            onView(matcher).check(matches(isDisplayed()))
        } finally {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }
}
