package ru.shcherbakovdv.ss.trainee.project_classes

import android.net.ConnectivityManager
import android.net.Network
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class NetworkCallback : ConnectivityManager.NetworkCallback() {

    private val networkStatePublisher = PublishSubject.create<Boolean>()

    val networkState: Observable<Boolean>
        get() {
            return networkStatePublisher
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    override fun onAvailable(network: Network?) {
        super.onAvailable(network)
        networkStatePublisher.onNext(true)
    }

    override fun onLost(network: Network?) {
        super.onLost(network)
        networkStatePublisher.onNext(false)
    }
}