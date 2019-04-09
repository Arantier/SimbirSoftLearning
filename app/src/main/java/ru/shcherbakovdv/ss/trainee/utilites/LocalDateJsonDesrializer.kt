package ru.shcherbakovdv.ss.trainee.utilites

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.DateTimeException
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

class LocalDateJsonDesrializer : JsonDeserializer<LocalDate> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
        if (json!=null) {
            val s = json.asString
            return LocalDate.parse(json.asString, DateTimeFormatter.ISO_DATE)
        } else {
            throw DateTimeException("Bad Json parsing")
        }
    }
}