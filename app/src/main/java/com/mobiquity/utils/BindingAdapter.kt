package com.mobiquity.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.core.dto.PriceModel
import com.core.widget.ImageViewCustom
import com.core.widget.TextViewCustom
import com.mobiquity.adapter.MainListAdapter
import com.mobiquity.adapter.MainPagerAdapter

class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("adapter")
        public fun RecyclerView.setAdapter(adapter : MainListAdapter?){
            this.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter?.let {
                this.adapter        = it
            }
        }


        @JvmStatic
        @BindingAdapter("price")
        public fun TextViewCustom.setPrice(price : PriceModel?){
            price?.let {
                this.setPrice(it)
            }
        }


        @JvmStatic
        @BindingAdapter("image")
        fun setImageLoading(view : ImageViewCustom, url : String?){
            url?.let {
                view.load(it)
            }
        }

        @JvmStatic
        @BindingAdapter("pagerAdapter")
        fun setAdapter(view : ViewPager, adapter : MainPagerAdapter?){
            adapter?.let {
                view.adapter = it
            }
        }

    }
}