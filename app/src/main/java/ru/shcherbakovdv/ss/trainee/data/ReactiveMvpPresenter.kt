package ru.shcherbakovdv.ss.trainee.data

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class ReactiveMvpPresenter<T : MvpView> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    protected fun attachDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}