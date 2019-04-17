package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable
import ru.shcherbakovdv.ss.trainee.CompositePresenter
import ru.shcherbakovdv.ss.trainee.dataclasses.CategoryProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : CompositePresenter<CategoryMvpView>() {

    fun requestCategories() {
        viewState.setLoadingState()
        CategoryProvider.requestCategoriesFile()
                .doOnSubscribe { viewState.setLoadingState() }
                .doOnError { viewState.setErrorState() }
                .subscribe { viewState.updateList(it)}
                .let { attachDisposable(it) }
    }
}