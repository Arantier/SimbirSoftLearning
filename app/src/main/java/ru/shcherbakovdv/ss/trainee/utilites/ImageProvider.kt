package ru.shcherbakovdv.ss.trainee.utilites

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.shcherbakovdv.ss.trainee.R

object ImageProvider {

    //Не
    fun loadImage(url: String, imageView: ImageView) {
        val options = RequestOptions().placeholder(R.drawable.image_user_placeholder)
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

    fun loadRoundedImage(url: String, imageView: ImageView) {
        loadImage(url, imageView)
    }

}