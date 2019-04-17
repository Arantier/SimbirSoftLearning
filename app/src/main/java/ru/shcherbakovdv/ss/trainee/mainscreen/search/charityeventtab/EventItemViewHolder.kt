package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.utilites.DateUtility
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider

class EventItemViewHolder(val view: View, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: CharityEvent) {
        view.apply {
            titleEventItem.text = event.title
            textEventItemDescription.text = event.description
            textEventItemDate.text = DateUtility.eventDateIntervalRepresentation(context,event.startDate,event.endDate)
            ImageProvider.loadImage(event.picturesUrlArray.first(),imageEventItem)
            setOnClickListener {
                onCharityEventClickListener.onCharityEventClick(event)
            }
        }
    }

}