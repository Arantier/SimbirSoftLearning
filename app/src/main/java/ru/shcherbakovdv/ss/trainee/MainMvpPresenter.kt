package ru.shcherbakovdv.ss.trainee

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.project_classes.NetworkCallback

@InjectViewState
class MainMvpPresenter : ReactiveMvpPresenter<MainMvpView>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }

    private val networkCallback = NetworkCallback()
    private var isConnected = false
        set(value) {
            if (value != field) {
                if (value) {
                    viewState.setConnectedState()
                } else {
                    viewState.setDisconnectedState()
                }
                field = value
            }
        }

    fun findContent(key: String) = SearchFieldNotifier.findContent(key)

    fun observeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        networkCallback.networkState
                .subscribe {
                    viewState.apply {
                        isConnected = it
                    }
                }.let { attachDisposable(it) }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}