package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
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
                .flatMap {
                    if (it) {
                        CharitiesProvider.requestAllCharities().toObservable()
                    } else {
                        viewState.setErrorState()
                        // Не слишком изящный способ, но Observable.error() не давало результат
                        Observable.empty<Array<Charity>>().doOnNext { throw IllegalStateException("Missing Internet connection") }
                    }
                }
                .subscribe({
                    if (!it.equals(CharitiesProvider.charities)) {
                        viewState.setLoadingState()
                        if (CharitiesProvider.charities == null) {
                            CharitiesProvider.charities = it
                        }
                        CharitiesProvider.charities?.let { array ->
                            fillScreen(array)
                        }
                    }
                }, {
                    setErrorScreen(it)
                }).let { attachDisposable(it) }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}