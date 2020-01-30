package ru.shcherbakovdv.ss.trainee.ui.search.charities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.CharityViewHolder
import ru.shcherbakovdv.ss.trainee.data.OnCharityClickListener

class CharitiesListAdapter(private val charityArray: Array<Charity>, private val onCharityClickListener : OnCharityClickListener) : RecyclerView.Adapter<CharityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, state: Int): CharityViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event_list, parent, false)
        return CharityViewHolder(view, onCharityClickListener)
    }

    override fun getItemCount() = charityArray.size

    override fun onBindViewHolder(viewHolder: CharityViewHolder, item: Int) = viewHolder.bind(charityArray[item])

}