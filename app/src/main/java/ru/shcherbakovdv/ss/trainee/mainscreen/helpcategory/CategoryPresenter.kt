package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.dataclasses.CategoryProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryMvpView>() {

    fun requestCategories() {
        viewState.setLoadingState()
        CategoryProvider.requestCategoriesFile()
                .subscribe({ array ->
                    viewState.updateList(array)
                }, ({ throwable ->
                    Logger.flatError(throwable.toString())
                    viewState.setErrorState()
                }))
    }
}