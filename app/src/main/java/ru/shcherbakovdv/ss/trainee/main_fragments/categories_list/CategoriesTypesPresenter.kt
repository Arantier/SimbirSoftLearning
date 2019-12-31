package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.CategoriesProvider

@InjectViewState
class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {
    fun requestCategories() {
        viewState.setLoadingState()
        if (CategoriesProvider.categories != null) {
            viewState.updateList(CategoriesProvider.categories!!)
        } else {
            CategoriesProvider.requestCategoriesFile()
                    .subscribe({ array -> CategoriesProvider.categories = array; viewState.updateList(array) }, { t: Throwable? -> viewState.setErrorState() })
                    .let { attachDisposable(it) }
        }
    }
}