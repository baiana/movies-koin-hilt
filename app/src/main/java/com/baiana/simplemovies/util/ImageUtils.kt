package com.baiana.simplemovies.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadWithGlide(url: String) {
    val options: RequestOptions = RequestOptions().transform(CenterCrop())

    Glide.with(this)
        .load(url).thumbnail(0.2f)
        .apply(options)
        .into(this)
}