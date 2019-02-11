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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile

class ProfileFragment : MvpAppCompatFragment(), ProfileMvpView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private lateinit var fragmentView: View

    override fun fillProfileScreen(profile: UserProfile) {
//TODO:Как только появится более точная система представления юзеров - переделай
        Glide.with(this)
                .setDefaultRequestOptions(RequestOptions().apply { placeholder(R.drawable.image_user_placeholder) })
                .load(profile.pictureUrl)
                .into(fragmentView.imageUserScreenPhoto)
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
            recyclerviewUserScreenFriends.adapter = FriendListAdapter(profile.friendsArray, object : OnFriendClickListener {
                override fun onFriendClick(friend: UserProfile) {
                    presenter.findAndShowProfile(friend)
                }
            })
        }
    }

    override fun showLoginScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        fragmentView.apply {
            recyclerviewUserScreenFriends.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            buttonUserScreenLogout.setOnClickListener {
                presenter.userLogout()
            }
        }
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        presenter.requestUserProfile()
    }

    companion object {
        @JvmStatic
        val TAG = "profile_fragment"

        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    class FriendListAdapter constructor(private val friendsList: Array<UserProfile>?,
                                        private val onFriendClickListener: OnFriendClickListener)
        : RecyclerView.Adapter<FriendListAdapter.ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend_list, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return friendsList?.size ?: 0
        }

        override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
            if (friendsList != null) {
                viewHolder.bind(friendsList[position], onFriendClickListener)
            }
        }

        class ItemViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(friend: UserProfile, listener: OnFriendClickListener) {
                Glide.with(view.context)
                        .load(friend.pictureUrl)
                        .into(view.imageFriendItem)
                view.textFriendItem.text = friend.name
                view.setOnClickListener { listener.onFriendClick(friend) }
            }
        }
    }

    interface OnFriendClickListener {
        fun onFriendClick(friend: UserProfile)
    }
}
