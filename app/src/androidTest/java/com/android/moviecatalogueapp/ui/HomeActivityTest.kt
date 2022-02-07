package com.android.moviecatalogueapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.android.moviecatalogueapp.R
import com.android.moviecatalogueapp.ui.home.HomeActivity
import com.android.moviecatalogueapp.utils.EspressoIdlingResource.idlingResource
import com.android.moviecatalogueapp.utils.NestedScrollViewTestUtils.betterScrollTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @Before
    fun init() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.img_detail_backdrop))
            .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_rating))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.cg_detail_genre))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_overview))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.img_detail_backdrop))
            .check(matches(isDisplayed()))

        onView(withId(R.id.img_detail_poster))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_rating))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.cg_detail_genre))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_overview))
            .perform(betterScrollTo())
            .check(matches(isDisplayed()))
    }

    @Test
    fun actionButtonShare() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withContentDescription(R.string.share))
            .perform(click())
    }

}