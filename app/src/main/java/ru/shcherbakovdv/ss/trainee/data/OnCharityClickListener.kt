package ru.shcherbakovdv.ss.trainee.data

import ru.shcherbakovdv.ss.trainee.data.Charity

interface OnCharityClickListener {
    fun onCharityEventClick(event: Charity)
}