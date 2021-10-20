package com.hyorim.sopt_assigmnet_1

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : Int){
        Glide.with(imageView.context).load(url)
            .into(imageView)
    }

}