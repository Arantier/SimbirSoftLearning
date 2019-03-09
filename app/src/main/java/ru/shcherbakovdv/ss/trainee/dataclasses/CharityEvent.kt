package ru.shcherbakovdv.ss.trainee.dataclasses

import org.threeten.bp.LocalDate

class CharityEvent(val title: String,
                   val description: String,
                   val picturesUrlArray: Array<String>,
                   val startDate: LocalDate,
                   val endDate: LocalDate,
                   val donatorsPicturesUrlArray: Array<String>)