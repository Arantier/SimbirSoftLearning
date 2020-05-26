package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

class CategoryPresenter : ReactiveMvpPresenter<CategoryMvpView>() {

    var categoryId = -1

    private val networkCallback = NetworkCallback()

    private fun fillScreen(events: Array<Charity>) {
        events.filter { it.categoryId == categoryId }
                .apply {
                    viewState.updateList(this.toTypedArray())
                }
    }

    private fun setErrorScreen(throwable: Throwable) {
        Logger.flatError(throwable.toString())
        viewState.setErrorState()
    }

    fun observeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        networkCallback.networkState
                .flatMap { isNetAvailable ->
                    if (!isNetAvailable) {
                        viewState.setErrorState()
                        throw IllegalStateException("Missing Internet connection")
                    }
                    CharitiesProvider.requestAllCharitiesAsArray()
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ fillScreen(it) }, { setErrorScreen(it) })
                .let { attachDisposable(it) }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}