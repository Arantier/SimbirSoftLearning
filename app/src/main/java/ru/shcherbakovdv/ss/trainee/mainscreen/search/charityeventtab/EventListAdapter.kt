package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

class EventListAdapter(private val eventArray: Array<CharityEvent>, private val onCharityEventClickListener : OnCharityEventClickListener) : RecyclerView.Adapter<EventItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): EventItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event_list, parent, false)
        return EventItemViewHolder(view, onCharityEventClickListener)
    }

    override fun getItemCount() = eventArray.size

    override fun onBindViewHolder(viewHolder: EventItemViewHolder, item: Int) = viewHolder.bind(eventArray[item])

}