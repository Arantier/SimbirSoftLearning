package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.CategoriesProvider

@InjectViewState
class CategoriesListPresenter : ReactiveMvpPresenter<CategoriesListMvpView>() {

    fun requestCategories() {
        viewState.setLoadingState()
        CategoriesProvider.requestCategoriesFile()
                .doOnSubscribe { viewState.setLoadingState() }
                .doOnError { viewState.setErrorState() }
                .subscribe { viewState.updateList(it)}
                .let { attachDisposable(it) }
    }
}