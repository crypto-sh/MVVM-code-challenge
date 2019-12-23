package com.mobiquity

import android.view.View
import android.view.ViewGroup
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.mobiquity.ui.DetailActivity
import com.mobiquity.ui.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get:Rule
    var testRule = CountingTaskExecutorRule()

    @get:Rule
    var rule = ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun step_0_useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.mobiquity.test", appContext.packageName)
    }

    @Test
    fun step_1_recyclerViewTest() {
        onView(withId(R.id.pager)).check(matches(isDisplayed()))
//        onView(withId(R.id.pager)).check(ViewPagerTabAssertion(2))

        onView(allOf(withId(R.id.recycler), isDescendantOfA(firstChildOf(withId(R.id.pager)))))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).let {
                val current = it.elementAt(0)
                Assert.assertEquals(current::class.java, DetailActivity::class.java)
            }
        }
    }

    private fun firstChildOf(parentMatcher: Matcher<View>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description!!.appendText("with first child view of type parentMatcher")
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view!!.parent !is ViewGroup) {
                    return parentMatcher.matches(view.parent)
                }
                val group = view.parent as ViewGroup
                return parentMatcher.matches(view.parent) && group.getChildAt(0) == view
            }
        }
    }
}