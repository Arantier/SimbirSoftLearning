package ru.shcherbakovdv.ss.trainee.utilites

import android.util.Log

object Logger {

    val FLAT_DEBUG_VALUE = "flatdebugtag"

    fun flatDebug(message:String){
        Log.d(FLAT_DEBUG_VALUE,message)
    }
}