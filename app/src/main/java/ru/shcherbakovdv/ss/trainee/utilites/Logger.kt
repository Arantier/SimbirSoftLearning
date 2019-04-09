package ru.shcherbakovdv.ss.trainee.utilites

import android.util.Log

object Logger {

    private const val FLAT_DEBUG_VALUE = "flatdebugtag"
    private const val FLAT_ERROR_VALUE = "flaterrortag"

    fun flatDebug(message:String){
        Log.d(FLAT_DEBUG_VALUE,message)
    }

    fun flatError(message:String){
        Log.e(FLAT_ERROR_VALUE,message)
    }
}