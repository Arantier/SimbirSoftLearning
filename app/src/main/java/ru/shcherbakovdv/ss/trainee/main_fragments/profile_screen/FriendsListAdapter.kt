package ru.shcherbakovdv.ss.trainee.main_fragments.profile_screen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data_classes.Profile
import ru.shcherbakovdv.ss.trainee.data_providers.ImageProvider

class FriendsListAdapter(private val friendsList: Array<Profile>?)
    : RecyclerView.Adapter<FriendsListAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = friendsList?.size ?: 0
    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        if (friendsList != null) {
            viewHolder.bind(friendsList[position])
        }
    }

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(friend: Profile) {
            ImageProvider.loadImage(friend.pictureUrl, view.imageFriendItem)
            view.textFriendItem.text = friend.name
        }
    }
}