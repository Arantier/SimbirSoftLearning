package ru.shcherbakovdv.ss.trainee.utilites

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.lang.reflect.Type

class LocalDateJsonSerializer : JsonSerializer<LocalDate> {

    override fun serialize(src: LocalDate?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if (src!=null) {
            return JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE))
        } else {
            throw DateTimeParseException("Bad Json source","01-01-1970",18)
        }
    }
}