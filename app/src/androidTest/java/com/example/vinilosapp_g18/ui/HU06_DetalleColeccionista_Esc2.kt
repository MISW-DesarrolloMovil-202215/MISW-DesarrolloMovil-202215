package com.example.vinilosapp_g18.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilosapp_g18.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HU06_DetalleColeccionista_Esc2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun hU06_DetalleColeccionista_Esc1() {
        val textView = onView(
            allOf(
                withText("Vinilos"),
                withParent(
                    allOf(
                        withId(R.id.hometext),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withText("Vinilos"),
                withParent(
                    allOf(
                        withId(R.id.hometext),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Vinilos")))

        val linearLayout = onView(
            allOf(
                withId(R.id.menu),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val imageButton = onView(
            allOf(
                withId(R.id.imgButtoncoleccionista),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.btnColeccionistas), withText("COLECCIONISTA"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.btnColeccionistas), withText("COLECCIONISTA"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.imgButtoncoleccionista),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
        Thread.sleep(4000)

        val recyclerView = onView(
            allOf(
                withId(R.id.coleccionistaRv),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.coleccionistaRv),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(7000)

        val recyclerView3 = onView(
            allOf(
                withId(R.id.commentsRv),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        recyclerView3.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.textView6),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.textView5),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )


        for (i in 1..5){
            textView4.check(matches(isDisplayed()))
            Espresso.pressBack()
            Thread.sleep(4500)
            recyclerView2.perform(click())
            Thread.sleep(4500)
        }

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
