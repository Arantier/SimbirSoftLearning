package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

interface OnCharityEventClickListener {
    fun onCharityEventClick(event: CharityEvent)
}