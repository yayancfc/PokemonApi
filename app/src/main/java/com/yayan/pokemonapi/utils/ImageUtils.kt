package com.yayan.pokemonapi.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {
    fun bindToImageView(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .asBitmap()
            .load(url)
            .into(imageView)
    }
}