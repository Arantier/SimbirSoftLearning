package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

class CategoryPresenter : ReactiveMvpPresenter<CategoryPageMvpView>() {

    var categoryId = -1

    lateinit var networkCallback: NetworkCallback

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
        networkCallback = NetworkCallback.newInstance(context)
            .apply {
                networkLiveState
                    .subscribeOn(Schedulers.io())
                    .subscribe({ netAvailable ->
                        if (netAvailable) {
                            CharitiesProvider.charitiesSingle
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    this@CategoryPresenter::fillScreen,
                                    this@CategoryPresenter::setErrorScreen
                                )
                        }
                    }, this@CategoryPresenter::setErrorScreen)
                    .let(this@CategoryPresenter::attachDisposable)
            }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}