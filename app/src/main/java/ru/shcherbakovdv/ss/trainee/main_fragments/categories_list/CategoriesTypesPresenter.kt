package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.CategoriesProvider

@InjectViewState
class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {

    fun requestCategories() {
        viewState.setLoadingState()
        CategoriesProvider.requestCategoriesFile()
                .doOnNext { viewState.setLoadingState() }
                .subscribe({array -> viewState.updateList(array)},{throwable -> viewState.setErrorState()})
                .let { attachDisposable(it) }
    }
}