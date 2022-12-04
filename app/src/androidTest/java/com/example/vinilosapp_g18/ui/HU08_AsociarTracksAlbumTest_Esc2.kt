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

@LargeTest
@RunWith(AndroidJUnit4::class)
class HU08_AsociarTracksAlbumTest_Esc2 {
    var testAlbum = "Test Album"+(0..1000000).random().toString()
    var testTrack:CustonTrack=
        CustonTrack(testAlbum,"TrackPol_"+(0..100).random().toString(),(2..4).random().toString()+":"+  (10..59).random().toString())

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun hU08_AsociarTracksAlbumTest_Esc2() {

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

        val button = onView(
            allOf(
                withId(R.id.albumes2), withText("ALBUMES"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val imageButton = onView(
            allOf(
                withId(R.id.albumes),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val imageButton2 = onView(
            allOf(
                withId(R.id.albumes),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageButton2.check(matches(isDisplayed()))

        val appCompatImageButtonh = onView(
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
        appCompatImageButtonh.perform(click())
        Thread.sleep(1500)

        val materialButton = onView(
            allOf(
                withId(R.id.btnCreateAlbum), withText("Crear Album"),
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
        materialButton.perform(click())
        Thread.sleep(1500)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.albumName_text_input),
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
        appCompatEditText.perform(replaceText(testTrack.nameAlbum), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.idEdtDate),
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
        appCompatEditText2.perform(click())
        Thread.sleep(1500)

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())
        Thread.sleep(1500)

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText3.perform(click())
        Thread.sleep(1500)

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText4.perform(click())
        Thread.sleep(1500)

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText5.perform(click())
        Thread.sleep(1500)

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText6.perform(click())
        Thread.sleep(1500)

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText7.perform(click())
        Thread.sleep(1500)

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.albumCover_text_input),
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
        appCompatEditText8.perform(
            replaceText("https://random.imagecdn.app/400/400"),
            closeSoftKeyboard()
        )

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.albumDescripcion_text_input),
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
        appCompatEditText9.perform(replaceText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultrices mi tempus imperdiet nulla malesuada pellentesque elit eget gravida."), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.btn_newAlbum), withText("Crear Album"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        Thread.sleep(2000)
        materialButton3.perform(click())
        Thread.sleep(1500)
        val albumListView = onView(
            allOf(
                withId(R.id.albumsRv)
            )
        )

        Thread.sleep(2000)
        albumListView.check(matches((isDisplayed())))

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
        Thread.sleep(1500)

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
        Thread.sleep(1500)

        val spinnerText = onData(hasToString(testTrack.nameAlbum))
        spinnerText.perform(click())
        Thread.sleep(1500)

        //AGREGA NOMBRE
        val trackName = onView(
            withId(R.id.trackName_text_input)
        )
        trackName.perform(replaceText(""), closeSoftKeyboard())
        Thread.sleep(1500)

            //AGREGA DURACION
        val trackDuration = onView(
            withId(R.id.trackDuration_text_input)
        )
        trackDuration.perform(replaceText(testTrack.durationTrack), closeSoftKeyboard())
        Thread.sleep(1500)



        val createTrack = onView(
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
        createTrack.perform(click())
        Thread.sleep(3000)

        val textViewh = onView(
            allOf(
                withId(R.id.text_tracks_album),
                withText(containsString("")),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textViewh.check(matches(isDisplayed()))
        Thread.sleep(2000)
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
