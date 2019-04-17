package ru.shcherbakovdv.ss.trainee.mainscreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.support.v4.content.ContextCompat
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable
import ru.shcherbakovdv.ss.trainee.NetworkCallback
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldNotifier

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpView>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }

    private val networkCallback = NetworkCallback()
    private lateinit var networkDisposable: Disposable
    private var isConnected = false
        set(value) {
            field = value
            resolveState(value, storageAvailable)
        }
    var storageAvailable = false
        set(value) {
            field = value
            resolveState(isConnected, value)
        }

    private fun resolveState(isConnected: Boolean, storageAvailable: Boolean) {
        if (isConnected && storageAvailable) {
            viewState.setConnectedState()
        } else {
            viewState.setUnconnectedState()
        }
    }

    fun findContent(key: String) = SearchFieldNotifier.findContent(key)

    fun observeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isConnected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        storageAvailable = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if (!storageAvailable) {
            viewState.requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, MainActivity.STORAGE_REQUEST_CODE)
        }
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        networkDisposable = networkCallback.networkState
                .subscribe {
                    viewState.apply {
                        isConnected = it
                    }
                }
    }

    fun disposeNetwork(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
        networkDisposable.dispose()
    }
}