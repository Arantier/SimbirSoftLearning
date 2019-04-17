package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.organisations_tab

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import ru.shcherbakovdv.ss.trainee.data_classes.Organisation

class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(organisation: Organisation) {
        view.text = organisation.name
    }

}