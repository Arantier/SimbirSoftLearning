package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event_list.view.*
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.utilites.LocaleStringManager.getLocaleQuantityString
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider

class EventItemViewHolder(val view: View, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: CharityEvent) {
        view.apply {
            titleEventItem.text = event.title
            textEventItemDescription.text = event.description
            val restDays = Period.between(event.endDate,event.startDate).days
            textEventItemDate.text = String.format(resources.getString(R.string.event_date_expiration_info),
                    getLocaleQuantityString(context,R.plurals.event_date_expiration, restDays),
                    event.startDate.format(DateTimeFormatter.ofPattern("dd.MM")),
                    event.endDate.format(DateTimeFormatter.ofPattern("dd.MM"))
            )
            ImageProvider.loadImage(event.picturesUrlArray.first(),imageEventItem)
            setOnClickListener {
                onCharityEventClickListener.onCharityEventClick(event)
            }
        }
    }

}