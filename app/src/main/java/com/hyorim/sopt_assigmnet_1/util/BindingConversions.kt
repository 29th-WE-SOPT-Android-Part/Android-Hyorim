package com.hyorim.sopt_assigmnet_1.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hyorim.sopt_assigmnet_1.R


object BindingConversions {

    /** follower_list 의 팔로워 프로필 */
    @BindingAdapter("imageUrl")       // Binding Adapter 생성
    @JvmStatic                                   // Static 함수로 설정해주기 위한 Annotation
    fun ImageView.loadImage( url : String){
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this)
    }

    /** fragment_profile 의 개인 프로필 */
    @BindingAdapter("imageSrc")
    @JvmStatic
    fun ImageView.loadImage(url : Int){
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this)
    }

    /** fragment_image 의 갤러리 이미지 */
    @BindingAdapter("galleryUri")
    @JvmStatic
    fun ImageView.galleryUri(url : Uri?){
        Glide.with(context)
            .load(url)
            .error(R.drawable.rectangle_fill_gray_btn)
            .into(this)
    }

}