package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.organisations_tab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data_classes.Organisation
import ru.shcherbakovdv.ss.trainee.data_classes.representations.OrganisationViewHolder

class OrganisationsListAdapter(private val organisationArray: Array<Organisation>) : RecyclerView.Adapter<OrganisationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): OrganisationViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_organisation_list, parent, false)
        return OrganisationViewHolder(view as TextView)
    }

    override fun getItemCount() = organisationArray.size

    override fun onBindViewHolder(viewHolder: OrganisationViewHolder, position: Int) = viewHolder.bind(organisationArray[position])
}