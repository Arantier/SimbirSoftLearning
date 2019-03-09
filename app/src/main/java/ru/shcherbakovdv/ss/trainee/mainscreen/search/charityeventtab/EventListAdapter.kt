package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_event_list.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import java.util.*

class EventListAdapter(private val eventArray: Array<CharityEvent>, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    class ViewHolder(val view: View, val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.ViewHolder(view) {

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
                val restDays = Period.between(LocalDate.now(),event.endDate).days
                textEventItemDate.text = String.format(resources.getString(R.string.event_date_expiration_info),
                        getLocaleQuantityString(R.plurals.event_date_expiration, restDays),
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

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event_list, parent, false)
        return ViewHolder(view, onCharityEventClickListener)
    }

    override fun getItemCount() = eventArray.size

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Int) = viewHolder.bind(eventArray[item])

}