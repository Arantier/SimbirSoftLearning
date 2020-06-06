package ru.shcherbakovdv.ss.trainee.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class NetworkCallback private constructor(): ConnectivityManager.NetworkCallback() {

    private val networkStateSubject = BehaviorSubject.createDefault(false)

    val networkLiveState: Observable<Boolean>
        get() = networkStateSubject

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        networkStateSubject.onNext(true)
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        networkStateSubject.onNext(false)
    }

    companion object {
        fun newInstance(context: Context): NetworkCallback =
            NetworkCallback().apply {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                connectivityManager.registerNetworkCallback(
                    NetworkRequest.Builder().build(),
                    this
                )
            }
    }
}