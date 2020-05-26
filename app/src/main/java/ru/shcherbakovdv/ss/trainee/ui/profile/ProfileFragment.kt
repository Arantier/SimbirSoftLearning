package ru.shcherbakovdv.ss.trainee.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Profile
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

class ProfileFragment : MvpAppCompatFragment(), ProfileMvpView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun fillProfileScreen(profile: Profile) {
        ImageProvider.loadImage(profile.pictureUrl, imageUserScreenPhoto)
        imageUserScreenPhoto.setOnClickListener {
            fragmentManager?.let { it1 ->
                EditPhotoDialog().show(it1, EditPhotoDialog.TAG)
            } ?: Logger.flatError("Fragment manager is null")
        }
        textUserScreenName.text = profile.name
        textUserScreenBirth.text = profile.birthDate
        textUserScreenBusiness.text = profile.business
        recyclerviewUserScreenFriends.adapter = FriendsListAdapter(profile.friends)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_profile_screen, container, false)

    companion object {
        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
