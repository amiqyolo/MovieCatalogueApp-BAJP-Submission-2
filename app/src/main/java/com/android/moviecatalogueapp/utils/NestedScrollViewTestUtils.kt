package com.android.moviecatalogueapp.utils

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers

object NestedScrollViewTestUtils {

    fun betterScrollTo(): ViewAction {
        return actionWithAssertions(NestedScrollToAction())
    }

    class NestedScrollToAction : ViewAction by ScrollToAction() {
        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(
                withEffectiveVisibility(Visibility.VISIBLE),
                isDescendantOfA(
                    Matchers.anyOf(
                        isAssignableFrom(ScrollView::class.java),
                        isAssignableFrom(HorizontalScrollView::class.java),
                        isAssignableFrom(NestedScrollView::class.java)
                    )
                )
            )
        }
    }

}