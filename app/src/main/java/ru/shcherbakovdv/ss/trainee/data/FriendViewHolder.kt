package ru.shcherbakovdv.ss.trainee.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_friend_list.view.*
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider

class FriendViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(friend: Profile) {
        ImageProvider.loadImage(friend.pictureUrl, view.imageFriendItem)
        view.textFriendItem.text = friend.name
    }
}