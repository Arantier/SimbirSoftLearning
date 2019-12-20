package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.data_classes.Category
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.CategoriesProvider

@InjectViewState
class CategoriesTypesPresenter : ReactiveMvpPresenter<CategoryTypesMvpView>() {
    var categories: Array<Category>? = null

    fun requestCategories() {
        viewState.setLoadingState()
        if (categories != null) {
            viewState.updateList(categories!!)
        } else {
            CategoriesProvider.requestCategoriesFile()
                    .subscribe({ array -> categories = array; viewState.updateList(array) }, { t: Throwable? -> viewState.setErrorState() })
                    .let { attachDisposable(it) }
        }
    }
}