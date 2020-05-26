package ru.shcherbakovdv.ss.trainee.ui.categories

import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CategoriesProvider

class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {
    fun requestCategories() {
        viewState.setLoadingState()
        CategoriesProvider.categories?.let {
            viewState.updateList(it)
        }
        if (CategoriesProvider.categories == null) {
            CategoriesProvider.requestCategoriesFile()
                    .subscribe({ array -> viewState.updateList(array) }, { t: Throwable? -> viewState.setErrorState() })
                    .let { attachDisposable(it) }
        }
    }
}