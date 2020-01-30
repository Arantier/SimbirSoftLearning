package ru.shcherbakovdv.ss.trainee.ui.categories

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CategoriesProvider

@InjectViewState
class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {
    fun requestCategories() {
        viewState.setLoadingState()
        if (CategoriesProvider.categories != null) {
            viewState.updateList(CategoriesProvider.categories!!)
        } else {
            CategoriesProvider.requestCategoriesFile()
                    .subscribe({ array -> viewState.updateList(array) }, { t: Throwable? -> viewState.setErrorState() })
                    .let { attachDisposable(it) }
        }
    }
}