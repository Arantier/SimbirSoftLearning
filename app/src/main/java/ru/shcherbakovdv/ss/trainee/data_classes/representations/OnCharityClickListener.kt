package ru.shcherbakovdv.ss.trainee.data_classes.representations

import ru.shcherbakovdv.ss.trainee.data_classes.Charity

interface OnCharityClickListener {
    fun onCharityEventClick(event: Charity)
}