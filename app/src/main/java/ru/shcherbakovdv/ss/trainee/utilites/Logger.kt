package ru.shcherbakovdv.ss.trainee.utilites

import android.util.Log

object Logger {

    private const val FLAT_DEBUG_VALUE = "flatdebugtag"
    private const val FLAT_ERROR_VALUE = "flaterrortag"
    private const val CURRENT_THREAD_TAG = "logcurrentthread"

    fun flatDebug(message: String?) {
        Log.d(FLAT_DEBUG_VALUE, message)
    }

    fun flatError(message: String?) {
        Log.e(FLAT_ERROR_VALUE, message)
    }

    fun logCurrentThread(message: String? = null) {
        if (message != null) {
            Log.d(
                CURRENT_THREAD_TAG,
                message + ". Current thread is: " + Thread.currentThread().name
            )
        } else {
            Log.d(CURRENT_THREAD_TAG, "Current thread is: " + Thread.currentThread().name)
        }
    }
}