package com.example.vinilosapp_g18

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image")
fun loadImage(view : ImageView, url: String){
    Glide.with(view).load(url).apply(RequestOptions().override(80, 230)).apply(RequestOptions().placeholder(R.drawable.loading_animation)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_broken_image)).into(view)
}

@BindingAdapter("imageDetail")
fun loadImageDetail(view : ImageView, url: String){
    Glide.with(view).load(url).apply(RequestOptions().override(230, 230)).apply(RequestOptions().placeholder(R.drawable.loading_animation)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_broken_image)).into(view)
}
