package com.testing.openweatherapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.testing.openweatherapp.R

object ImageLoader {
    fun loadImage(view: ImageView, url: String, placeholder: Int = R.drawable.ic_baseline_image) {
        Glide.with(view)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .into(view)
    }
}