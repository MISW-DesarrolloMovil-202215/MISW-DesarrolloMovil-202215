package com.example.vinilosapp_g18.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilosapp_g18.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.vinilosapp_g18.ui.CustonTrack

@LargeTest
@RunWith(AndroidJUnit4::class)
class HU08_AsociarTracksAlbumTest_Esc6 {
    var testTrack:CustonTrack=
        CustonTrack("Test1","TrackPol_"+(0..99).random().toString(),(0..99).random().toString()+":"+  (10..59).random().toString())

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun hU08_AsociarTracksAlbumTest_Esc6() {
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.albumes),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.btnImgCreateTrack),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.album_spinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())



        val appCompatEditText = onView(
            allOf(
                withId(R.id.trackName_text_input),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        //AGREGA NOMBRE AL TRACK
        appCompatEditText.perform(replaceText(testTrack.nameTrack), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.trackDuration_text_input),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )

        //AGREGA NOMBRE DURACION
        appCompatEditText2.perform(replaceText(testTrack.durationTrack), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btn_newAlbum), withText("Crear Track"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.text_tracks_album),
                withText("polo    9:33\nlop    9:00\nhmnmnmnn    5:00\nKILOM ETRICO9    77:00\nLIPO    88:00\npista 0021    uigasuygsauyags uysa\ntestu    0:35\npp    5:55\nKILOM ETRICO5    77:00\nABCA    99:59\nAAA    12:12\npolo    6:00\n"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
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
