package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.util.Log

object Interactor {

    val ERROR_TAG = "no_observers_found"

    private val listOfObservers: ArrayList<Observer> = ArrayList()

    fun attachObserver(observer: Observer) {
        listOfObservers.add(observer)
    }

    fun detachObserver(observer: Observer) {
        listOfObservers.remove(observer)
    }

    fun findContent(key: String?) {
            listOfObservers.find { it.isActive }
                    ?.requestContent(key) ?: Log.e(ERROR_TAG, "No observers found")
    }
}