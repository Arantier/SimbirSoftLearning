package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
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
    private var isConnected: Boolean? = null
        set(value) {
            if (value != field) {
                if (value == true) {
                    viewState.setConnectedState()
                } else {
                    viewState.setDisconnectedState()
                }
                field = value
            }
        }

    fun prepareForSearch() {
        viewState.showSearchBar()
    }

    fun findContent(key: String) {
        SearchFieldNotifier.searchField.onNext(key)
    }

    fun observeNetwork(context: Context) {
        networkCallback = NetworkCallback.newInstance(context)
            .apply {
                networkLiveState
                    .subscribe {
                        viewState.apply {
                            isConnected = it
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