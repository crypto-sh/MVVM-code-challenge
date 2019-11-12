package com.core.widget


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class ButtonCustom : AppCompatButton {

    constructor(context: Context?) : super(context)

    @SuppressLint("Recycle")
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}