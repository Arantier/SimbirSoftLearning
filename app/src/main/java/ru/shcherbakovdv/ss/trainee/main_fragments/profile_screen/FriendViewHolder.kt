package ru.shcherbakovdv.ss.trainee.main_fragments.profile_screen

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovdv.ss.trainee.data_classes.Profile
import ru.shcherbakovdv.ss.trainee.data_providers.ImageProvider

class FriendViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(friend: Profile) {
        ImageProvider.loadImage(friend.pictureUrl, view.imageFriendItem)
        view.textFriendItem.text = friend.name
    }
}