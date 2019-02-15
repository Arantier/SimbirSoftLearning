package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab

import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.utilites.ImageProvider
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.CharityEvent
import java.util.*

class EventListAdapter(private val eventArray: Array<CharityEvent>) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event_list, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount() = eventArray.size

    override fun onBindViewHolder(viewHolder: EventViewHolder, item: Int) = viewHolder.bind(eventArray[item])

}