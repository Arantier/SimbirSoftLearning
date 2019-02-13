package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.utilites.ImageProvider
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile

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
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        fragmentView.apply {
            recyclerviewUserScreenFriends.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        return fragmentView
    }

    companion object {
        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}
