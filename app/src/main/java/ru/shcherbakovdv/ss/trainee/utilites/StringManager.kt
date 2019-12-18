package ru.shcherbakovdv.ss.trainee.utilites

import android.content.Context
import android.content.res.Configuration
import java.util.*

object StringManager {
    fun getLocaleQuantityString(context: Context, id: Int, quantity: Int): String {
        val configuration = Configuration(context.resources.configuration).apply {
            setLocale(Locale("ru"))
        }
        return context.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
    }
}