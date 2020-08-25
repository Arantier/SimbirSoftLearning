package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

class MainMvpPresenter : ReactiveMvpPresenter<MainMvpView>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }

    lateinit var networkCallback: NetworkCallback

    fun findContent(key: String) {
        SearchFieldNotifier.searchField.onNext(key)
    }

    fun observeNetwork(context: Context) {
        networkCallback = NetworkCallback.newInstance(context)
            .apply {
                networkLiveState
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { connected ->
                        viewState.apply {
                            if (connected) {
                                setConnectedState()
                            } else {
                                setDisconnectedState()
                            }
                        }
                    }.let { attachDisposable(it) }
            }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}