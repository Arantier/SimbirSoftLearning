package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovdv.ss.trainee.data_classes.Charity
import ru.shcherbakovdv.ss.trainee.utilites.DateUtility
import ru.shcherbakovdv.ss.trainee.data_providers.ImageProvider

class EventItemViewHolder(val view: View, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: Charity) {
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