package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab

import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.CharityEvent
import ru.shcherbakovDmitry.ss.androidTraineeEducation.utilites.ImageProvider
import java.util.*

class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private fun getLocaleQuantityString(id: Int, quantity: Int): String {
        val configuration = Configuration(view.context!!.resources.configuration).apply {
            setLocale(Locale("ru"))
        }
        return view.context!!.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
    }

    fun bind(event: CharityEvent) {
        view.apply {
            titleEventItem.text = event.title
            textEventItemDescription.text = event.description
            val startDateString = event.startDate.split(".")
            val startDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, startDateString[2].toInt())
                set(Calendar.MONTH, startDateString[1].toInt())
                set(Calendar.DAY_OF_MONTH, startDateString[0].toInt())
            }
            val endDateString = event.endDate.split(".")
            val endDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, endDateString[2].toInt())
                set(Calendar.MONTH, endDateString[1].toInt())
                set(Calendar.DAY_OF_MONTH, endDateString[0].toInt())
            }
            var dayDifference = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)
            if (endDate.get(Calendar.DAY_OF_YEAR) < startDate.get(Calendar.DAY_OF_YEAR)) {
                dayDifference += if (startDate.get(Calendar.YEAR) % 4 == 0) {
                    366
                } else {
                    365
                }
            }
            textEventItemDate.text = String.format(resources.getString(R.string.event_date_expiration_info),
                    getLocaleQuantityString(R.plurals.event_date_expiration, dayDifference),
                    startDateString[0].toInt(),
                    startDateString[1].toInt(),
                    endDateString[0].toInt(),
                    endDateString[1].toInt()
            )
            ImageProvider.loadImage(event.pictureURL,imageEventItem)
        }
    }

}