package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.data.OrganisationViewHolder

class OrganisationsListAdapter(private val organisations: Array<Organisation>) : RecyclerView.Adapter<OrganisationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): OrganisationViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_organisation_list, parent, false)
        return OrganisationViewHolder(view as TextView)
    }

    override fun getItemCount() = organisations.size

    override fun onBindViewHolder(viewHolder: OrganisationViewHolder, position: Int) = viewHolder.bind(organisations[position])
}