package ru.shcherbakovdv.ss.trainee.mainscreen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.UserProfile
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider

class ProfileFragment : MvpAppCompatFragment(), ProfileMvpView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private lateinit var fragmentView: View

    override fun fillProfileScreen(profile: UserProfile) {
        ImageProvider.loadImage(profile.pictureUrl, fragmentView.imageUserScreenPhoto)
        fragmentView.apply {
            imageUserScreenPhoto.apply {
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_XY
                setOnClickListener {
                    EditPhotoDialog().show(fragmentManager, EditPhotoDialog.TAG)
                }
            }
            textUserScreenName.text = profile.name
            textUserScreenBirth.text = profile.birthDate
            textUserScreenBusiness.text = profile.business
            recyclerviewUserScreenFriends.adapter = FriendListAdapter(profile.friendsArray)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View {
        fragmentView = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        return fragmentView
    }

    companion object {
        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
