package ru.shcherbakovdv.ss.trainee.data.representations

import ru.shcherbakovdv.ss.trainee.data.Charity

interface OnCharityClickListener {
    fun onCharityEventClick(event: Charity)
}