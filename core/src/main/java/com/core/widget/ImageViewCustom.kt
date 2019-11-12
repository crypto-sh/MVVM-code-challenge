package com.core.widget




import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.core.BuildConfig
import com.core.R


class ImageViewCustom : AppCompatImageView {

    private var isSquare : Boolean = false

    private val request = Glide.with(this)

    constructor(context: Context?) : super(context)

    @SuppressLint("Recycle")
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        context?.let {
            val typed = it.obtainStyledAttributes(attrs, R.styleable.ImageViewCustom)
            isSquare = typed.getBoolean(R.styleable.ImageViewCustom_isSquare, false)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, if (isSquare) widthMeasureSpec  else heightMeasureSpec)
    }

    fun load(url: String) {
        request.load(BuildConfig.baseUrl + url)
            .fitCenter()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(ContextCompat.getDrawable(this.context, R.drawable.place_holder_image))
            .into(this)
    }
}