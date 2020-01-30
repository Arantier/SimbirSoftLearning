package ru.shcherbakovdv.ss.trainee.utilites.extensions

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.getClassIntent() = Intent(this, T::class.java)