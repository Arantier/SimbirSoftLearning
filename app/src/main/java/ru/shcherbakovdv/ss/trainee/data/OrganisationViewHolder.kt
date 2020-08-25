package ru.shcherbakovdv.ss.trainee.data

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

    fun bind(organisation: Organisation) {
        view.text = organisation.name
    }

}