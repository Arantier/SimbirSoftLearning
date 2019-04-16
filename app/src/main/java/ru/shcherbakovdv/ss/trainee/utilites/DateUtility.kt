package ru.shcherbakovdv.ss.trainee.utilites

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.temporal.ChronoUnit

object DateUtility {

    fun daysRest(date : LocalDate): Int {
        val rest = ChronoUnit.DAYS.between(LocalDate.now(ZoneId.of("UTC+3")), date).toInt()
        return if (rest>0) rest else 0
    }
}