package ru.shcherbakovdv.ss.trainee.mainscreen.profile

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovdv.ss.trainee.dataclasses.UserProfile
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider

class FriendViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(friend: UserProfile) {
        ImageProvider.loadImage(friend.pictureUrl, view.imageFriendItem)
        view.textFriendItem.text = friend.name
    }
}