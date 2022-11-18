package com.example.vinilosapp_g18.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.vinilosapp_g18.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
<<<<<<< HEAD
=======

>>>>>>> develop
class HU01_ListarAlbumesTest_Esc3 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listarAlbumesTest_Esc3() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.vinilosapp_g18", appContext.packageName)

        Thread.sleep(2000)
        val linearLayout = onView(
            allOf(
                withId(R.id.hometext),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

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
        textView.check(matches(withText("Vinilos")))

        val textView2 = onView(
            allOf(
                withText("Explorar el mundo a través de tus oidos."),
                withParent(
                    allOf(
                        withId(R.id.hometext),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Explorar el mundo a través de tus oidos.")))

        val linearLayout2 = onView(
            allOf(
                withId(R.id.menu),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        linearLayout2.check(matches(isDisplayed()))

        val view = onView(
            allOf(
                withId(android.R.id.statusBarBackground),
                withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java)),
                isDisplayed()
            )
        )
        view.check(matches(isDisplayed()))

        val albumsButton = onView(
            allOf(
                withId(R.id.albumes2), withText("Albumes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        albumsButton.perform(click())
        Thread.sleep(2000)


        val recyclerView = onView(
            allOf(
                withId(R.id.albumsRv),
                isDisplayed()
            )
        )

        for (i in 1..5){
            recyclerView.check(matches((isDisplayed())))
            pressBack()
            Thread.sleep(2000)
            albumsButton.perform(click())
            Thread.sleep(2000)
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
