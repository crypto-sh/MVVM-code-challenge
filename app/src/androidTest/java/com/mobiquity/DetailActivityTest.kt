package com.mobiquity


import android.content.Intent
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.core.dto.PriceModel
import com.core.dto.ProductModel
import com.mobiquity.ui.DetailActivity
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.allOf
import org.junit.Before
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
class DetailActivityTest {

    lateinit var productModel : ProductModel

    @get:Rule
    var rule = ActivityTestRule(DetailActivity::class.java, false, false)

    @Before
    fun startActivityDetail(){
        val price = PriceModel(1.00, "EUR")
        productModel = ProductModel("123", "345", "product", "/Cake.jpg", "description", price)
        val intent = Intent().apply {
            putExtra(IntentManager.Product_KEY,productModel)
        }
        rule.launchActivity(intent)
    }

    @Test
    fun step_0_checkDetailTest() {
        onView(withId(R.id.textPrice)).check(matches(withText(productModel.salePrice.toString())))
        onView(withId(R.id.textDescription)).check(matches(withText(productModel.description)))
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText(productModel.name)))
    }


}