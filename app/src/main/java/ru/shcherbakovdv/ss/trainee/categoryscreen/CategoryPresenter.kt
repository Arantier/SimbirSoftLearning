package ru.shcherbakovdv.ss.trainee.categoryscreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable
import ru.shcherbakovdv.ss.trainee.NetworkCallback
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.dataclasses.EventProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryMvpView>() {

    var categoryId = -1

    private val networkCallback = NetworkCallback()
    private lateinit var networkDisposable: Disposable
    private lateinit var eventsDisposable: Disposable

    private fun fillScreen(events: Array<CharityEvent>) {
        events.filter { it.categoryId == categoryId }
                .apply {
                    viewState.updateList(this.toTypedArray())
                }
        eventsDisposable.dispose()
    }

    private fun setErrorScreen(throwable: Throwable) {
        eventsDisposable.dispose()
        Logger.flatError(throwable.toString())
        viewState.setErrorState()
    }

    fun observeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        if (isConnected) {
            viewState.setLoadingState()
            eventsDisposable = EventProvider.requestAllEvents()
                    .subscribe(({ array -> fillScreen(array) }),
                            ({ throwable ->
                                setErrorScreen(throwable)
                            }))
        } else {
            viewState.setErrorState()
        }
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        networkDisposable = networkCallback.networkState
                .subscribe {
                    if (it) {
                        viewState.setLoadingState()
                        eventsDisposable = EventProvider.requestAllEvents()
                                .subscribe(({ array -> fillScreen(array) }),
                                        ({ throwable ->
                                            setErrorScreen(throwable)
                                        }))
                    } else {
                        viewState.setErrorState()
                    }
                }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
        networkDisposable.dispose()
    }
}