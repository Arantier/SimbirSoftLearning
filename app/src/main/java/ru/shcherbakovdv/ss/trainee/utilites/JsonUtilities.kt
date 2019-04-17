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

}