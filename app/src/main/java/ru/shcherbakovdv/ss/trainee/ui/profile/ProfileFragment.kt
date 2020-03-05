package ru.shcherbakovdv.ss.trainee.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Profile
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider

class ProfileFragment : MvpAppCompatFragment(), ProfileMvpView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun fillProfileScreen(profile: Profile) {
        ImageProvider.loadImage(profile.pictureUrl, imageUserScreenPhoto)
        imageUserScreenPhoto.setOnClickListener {
            EditPhotoDialog().show(fragmentManager, EditPhotoDialog.TAG)
        }
        textUserScreenName.text = profile.name
        textUserScreenBirth.text = profile.birthDate
        textUserScreenBusiness.text = profile.business
        recyclerviewUserScreenFriends.adapter = FriendsListAdapter(profile.friendsArray)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_profile_screen, container, false)

    companion object {
        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
