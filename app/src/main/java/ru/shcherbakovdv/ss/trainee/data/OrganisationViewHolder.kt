package ru.shcherbakovdv.ss.trainee.data

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import ru.shcherbakovdv.ss.trainee.data.Organisation

class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(organisation: Organisation) {
        view.text = organisation.name
    }

}