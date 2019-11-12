package com.core.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.core.dto.PriceModel

class TextViewCustom : AppCompatTextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    @SuppressLint("SetTextI18n")
    fun setPrice(price : PriceModel){
        text = price.toString()
    }

}