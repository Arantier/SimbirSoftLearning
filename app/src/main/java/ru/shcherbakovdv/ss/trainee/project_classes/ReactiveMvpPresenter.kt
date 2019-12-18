package ru.shcherbakovdv.ss.trainee.project_classes

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ReactiveMvpPresenter<T : MvpView> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    protected fun attachDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}