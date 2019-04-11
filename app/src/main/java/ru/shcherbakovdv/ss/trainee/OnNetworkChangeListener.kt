package ru.shcherbakovdv.ss.trainee

interface OnNetworkChangeListener {

    fun connectionAvailable()

    fun connectionLost()
}