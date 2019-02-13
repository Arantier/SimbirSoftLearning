package ru.shcherbakovDmitry.ss.androidTraineeEducation.utilites

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R

object ImageProvider {

    //Не
    fun loadImage(url:String, imageView: ImageView){
        val options = RequestOptions().placeholder(R.drawable.image_user_placeholder)
        Glide.with(imageView.context)
                .load(url)
                .apply(options)
                .into(imageView)
    }

}