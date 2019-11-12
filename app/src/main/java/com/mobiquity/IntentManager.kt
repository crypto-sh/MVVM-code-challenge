package com.mobiquity

import android.content.Context
import android.content.Intent
import com.core.dto.ProductModel
import com.mobiquity.ui.DetailActivity
import java.lang.ref.WeakReference

object IntentManager {

    const val Product_KEY = "PRODUCT_KEY"

    fun startDetailActivity(context: WeakReference<Context>, product : ProductModel){
        context.get()?.let {
            val intent = Intent(it, DetailActivity::class.java)
            intent.putExtra(Product_KEY, product)
            it.startActivity(intent)
        }
    }
}