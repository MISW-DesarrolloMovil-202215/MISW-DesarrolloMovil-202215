package com.example.vinilosapp_g18

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image")
fun loadImage(view : ImageView, url: String){
    Glide.with(view).load(url).apply(RequestOptions().override(80, 200)).into(view)

}