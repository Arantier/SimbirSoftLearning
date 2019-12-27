package ru.shcherbakovdv.ss.trainee.utilites

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.threeten.bp.LocalDate

object JsonUtilities {

    val gson: Gson by lazy {
        GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateJsonDesrializer())
                .registerTypeAdapter(LocalDate::class.java, LocalDateJsonSerializer())
                .create()
    }

    fun toJson(source: Any): String {
        return gson.toJson(source)
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>): T? {
        return gson.fromJson(json, classOfT)
    }
}