package ru.shcherbakovdv.ss.trainee

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.Disposable

abstract class CompositePresenter<T : MvpView> : MvpPresenter<T>() {

    private val disposables = ArrayList<Disposable>()

    protected fun attachDisposable(disposable: Disposable) = disposables.add(disposable)

    override fun onDestroy() {
        super.onDestroy()
        disposables.apply{
            forEach { it.dispose() }
            clear()
        }
    }
}