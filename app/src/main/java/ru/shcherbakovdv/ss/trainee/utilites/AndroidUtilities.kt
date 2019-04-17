package ru.shcherbakovdv.ss.trainee.utilites

import android.content.Context
import android.content.Intent
import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

inline fun <reified T> Context.getClassIntent() = Intent(this, T::class.java)