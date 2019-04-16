package ru.shcherbakovdv.ss.trainee.dataclasses

import org.threeten.bp.LocalDate

data class CharityEvent(val categoryId: Int,
                   val title: String,
                   val description: String,
                   val picturesUrlArray: Array<String>,
                   val startDate: LocalDate,
                   val endDate: LocalDate,
                   val organisation: Organisation,
                   val donatorsPicturesUrlArray: Array<String>)