package com.hyorim.sopt_assigmnet_1

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

object BindingConversions {

    /** follower_list 의 팔로워 프로필 */
    @BindingAdapter("imageUrl")       // Binding Adapter 생성
    @JvmStatic                                   // Static 함수로 설정해주기 위한 Annotation
    fun loadImage(imageView : ImageView, url : String){
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }

    /** fragment_profile 의 개인 프로필 */
    @BindingAdapter("imageSrc")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : Int){
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }

}