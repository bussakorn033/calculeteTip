package com.example.caltip

//import androidx.test.platform.app.InstrumentationRegistry
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//import org.junit.Test
//import org.junit.runner.RunWith
//
//import org.junit.Assert.*
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//class ExampleInstrumentedTest {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.caltip", appContext.packageName)
//    }
//}


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

//        onView(withId(R.id.calculateButton)).perform(click())
        onView(withId(R.id.calculateButton)).check(matches(isDisplayed()));


        onView(withId(R.id.tipResult))
            .check(matches(withText(containsString("$10.00"))))
    }
}