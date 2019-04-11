package ru.shcherbakovdv.ss.trainee

import android.net.ConnectivityManager
import android.net.Network

class NetworkCallback(val onNetworkChangeListener: OnNetworkChangeListener) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        onNetworkChangeListener.connectionAvailable()
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        onNetworkChangeListener.connectionLost()
    }
}