package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile
import ru.shcherbakovDmitry.ss.androidTraineeEducation.utilites.ImageProvider

class FriendListAdapter(private val friendsList: Array<UserProfile>?)
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
            viewHolder.bind(friendsList[position])
        }
    }

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(friend: UserProfile) {
            ImageProvider.loadImage(friend.pictureUrl, view.imageFriendItem)
            view.textFriendItem.text = friend.name
        }
    }
}