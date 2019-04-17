package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable
import ru.shcherbakovdv.ss.trainee.dataclasses.CategoryProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryMvpView>() {

    fun requestCategories() : Disposable {
        viewState.setLoadingState()
        return CategoryProvider.requestCategoriesFile()
                .doOnSubscribe { viewState.setLoadingState() }
                .doOnError { viewState.setErrorState() }
                .subscribe(viewState::updateList) { Logger.flatError(it.toString()) }
    }
}