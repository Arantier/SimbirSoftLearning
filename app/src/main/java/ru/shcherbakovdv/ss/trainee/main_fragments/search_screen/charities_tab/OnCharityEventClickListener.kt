package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab

import ru.shcherbakovdv.ss.trainee.data_classes.Charity

interface OnCharityEventClickListener {
    fun onCharityEventClick(event: Charity)
}