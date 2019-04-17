package ru.shcherbakovdv.ss.trainee.utilites

import android.content.Context
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import ru.shcherbakovdv.ss.trainee.R

object DateUtility {

    fun daysDiffer(start: LocalDate, end: LocalDate) = ChronoUnit.DAYS.between(start, end).toInt()

    fun daysRest(date : LocalDate): Int {
        val rest = daysDiffer(LocalDate.now(ZoneId.of("UTC+3")), date)
        return if (rest>0) rest else 0
    }

    fun eventDateIntervalRepresentation(context: Context, start: LocalDate, end: LocalDate) : String {
        return String.format(context.getString(R.string.event_date_expiration_info),
                StringManager.getLocaleQuantityString(context,
                        R.plurals.event_date_expiration,
                        daysRest(end)),
                dateToString(start),
                dateToString(end))
    }

    fun dateToString(date: LocalDate) : String{
        return date.format(DateTimeFormatter.ofPattern("dd.MM"))
    }
}