package ru.shcherbakovdv.ss.trainee.data

import android.net.ConnectivityManager
import android.net.Network
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class NetworkCallback : ConnectivityManager.NetworkCallback() {

    private val networkStateSubject = BehaviorSubject.createDefault(false)

    val networkState: Observable<Boolean>
        get() = networkStateSubject
                .observeOn(AndroidSchedulers.mainThread())

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        networkStateSubject.onNext(true)
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        networkStateSubject.onNext(false)
    }
}