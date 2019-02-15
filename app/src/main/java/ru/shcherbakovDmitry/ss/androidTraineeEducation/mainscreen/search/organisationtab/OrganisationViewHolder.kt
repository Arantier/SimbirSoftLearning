package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.Organisation

class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(organisation: Organisation) {
        view.text = organisation.name
    }

}