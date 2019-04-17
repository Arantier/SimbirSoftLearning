package ru.shcherbakovdv.ss.trainee.categoryscreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable
import ru.shcherbakovdv.ss.trainee.CompositePresenter
import ru.shcherbakovdv.ss.trainee.NetworkCallback
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.dataclasses.EventProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : CompositePresenter<CategoryMvpView>() {

    var categoryId = -1

    private val networkCallback = NetworkCallback()

    private fun fillScreen(events: Array<CharityEvent>) {
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
        val isConnected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        if (isConnected) {
            viewState.setLoadingState()
            EventProvider.requestAllEvents()
                    .subscribe(({ array -> fillScreen(array) }),
                            ({ throwable ->
                                setErrorScreen(throwable)
                            }))
                    .let { attachDisposable(it) }
        } else {
            viewState.setErrorState()
        }
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        networkCallback.networkState
                .subscribe {
                    if (it) {
                        viewState.setLoadingState()
                        EventProvider.requestAllEvents()
                                .subscribe(({ array -> fillScreen(array) }),
                                        ({ throwable ->
                                            setErrorScreen(throwable)
                                        })).let { attachDisposable(it) }
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