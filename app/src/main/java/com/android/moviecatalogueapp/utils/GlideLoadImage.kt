package com.android.moviecatalogueapp.utils

import android.widget.ImageView
import com.android.moviecatalogueapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object GlideLoadImage {

    fun ImageView.load(image: Any?) {
        Glide.with(context.applicationContext)
            .load(image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error_image))
            .into(this)
    }

}