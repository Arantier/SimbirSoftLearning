package ru.shcherbakovdv.ss.trainee.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.DateUtils

class CharityViewHolder(val view: View, private val onCharityClickListener : OnCharityClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: Charity) {
        view.apply {
            titleEventItem.text = event.title
            textEventItemDescription.text = event.description
            textEventItemDate.text = DateUtils.eventDateIntervalRepresentation(context,event.startDate,event.endDate)
            ImageProvider.loadImage(event.picturesUrls.first(),imageEventItem)
            setOnClickListener {
                onCharityClickListener.onCharityEventClick(event)
            }
        }
    }

}