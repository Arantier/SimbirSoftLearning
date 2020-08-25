package ru.shcherbakovdv.ss.trainee.ui.categories

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CategoriesProvider

class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {

    private val ERROR_TAG = "Request categories err"

    fun requestCategories() {
        viewState.setLoadingState()
        CategoriesProvider.categoriesSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(viewState::updateList) {
                Log.e(ERROR_TAG, it?.message ?: "Unknown error")
                viewState.setErrorState()
            }.let(this::attachDisposable)
    }
}