package com.mobiquity

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.viewpager.widget.ViewPager
import org.junit.Assert


class ViewPagerTabAssertion(private val itemExpected : Int) : ViewAssertion {
    /**
     * Checks the state of the given view (if such a view is present).
     *
     * @param view the view, if one was found during the view interaction or null if it was not (which
     * may be an acceptable option for an assertion)
     * @param noViewFoundException an exception detailing why the view could not be found or null if
     * the view was found
     */
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val pager = view as ViewPager
        val count = pager.adapter?.count ?: 0
        Assert.assertEquals(count, itemExpected)
    }
}