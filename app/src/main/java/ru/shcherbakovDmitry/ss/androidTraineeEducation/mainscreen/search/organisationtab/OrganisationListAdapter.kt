package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.Organisation

class OrganisationListAdapter(private val organisationArray: Array<Organisation>) : RecyclerView.Adapter<OrganisationListAdapter.OrganisationViewHolder>() {

    class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

        fun bind(organisation: Organisation) {
            view.text = organisation.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): OrganisationViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_organisation_list, parent, false)
        return OrganisationViewHolder(view as TextView)
    }

    override fun getItemCount(): Int {
        return organisationArray.size
    }

    override fun onBindViewHolder(viewHolder: OrganisationViewHolder, position: Int) {
        viewHolder.bind(organisationArray[position])
    }
}