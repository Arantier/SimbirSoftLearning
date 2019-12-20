package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.data_classes.Charity
import ru.shcherbakovdv.ss.trainee.data_providers.EventsProvider
import ru.shcherbakovdv.ss.trainee.project_classes.NetworkCallback
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : ReactiveMvpPresenter<CategoryMvpView>() {

    var categoryId = -1
    var charities: Array<Charity>? = null

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
                .subscribe {
                    if (it) {
                        viewState.setLoadingState()
                        if (charities == null) {
                            EventsProvider.requestAllEvents()
                                    .subscribe(({ array -> charities = array; fillScreen(array) }), ({ t: Throwable? -> setErrorScreen(t!!) }))
                                    .let { attachDisposable(it) }
                        } else {
                            fillScreen(charities!!)
                        }
                    } else {
                        viewState.setErrorState()
                    }
                }.let { attachDisposable(it) }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}