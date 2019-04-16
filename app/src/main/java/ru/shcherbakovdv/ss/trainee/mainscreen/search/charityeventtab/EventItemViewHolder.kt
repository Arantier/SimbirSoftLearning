package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event_list.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.LocaleStringManager.getLocaleQuantityString

class EventItemViewHolder(val view: View, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(event: CharityEvent) {
        view.apply {
            titleEventItem.text = event.title
            textEventItemDescription.text = event.description
            val restDays = ChronoUnit.DAYS.between(LocalDate.now(ZoneId.of("UTC+3")),event.endDate).toInt()
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