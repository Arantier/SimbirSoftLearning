package ru.shcherbakov_dmitry.ss.android_trainee_education

interface Observer {

    val isActive: Boolean

    fun requestContent(key: String?)
}