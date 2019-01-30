package ru.shcherbakov_dmitry.ss.android_trainee_education.ProfileScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_profile.view.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.R

class ProfileScreen : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        //TODO:Как только появится более точная система представления юзеров - переделай
        val userPhoto = view.image_user_photo
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_user_icon)
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load("https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/1D81E1C0-76EC-48C4-9272-A6411B832DF4.png")
                .into(userPhoto)
        userPhoto.adjustViewBounds = true
        userPhoto.scaleType = ImageView.ScaleType.FIT_XY
        userPhoto.setOnClickListener {
            val dialog = EditPhotoDialog()
            dialog.show(fragmentManager,getString(R.string.edit_photo_dialog_tag))
        }
        view.text_user_name.text = "Константинов Денис"
        view.text_user_birth_date.text = "01 февраля 1980"
        view.text_user_business.text = "Хирургия, травматология"
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() : ProfileScreen {
            return ProfileScreen()
        }
    }
}
