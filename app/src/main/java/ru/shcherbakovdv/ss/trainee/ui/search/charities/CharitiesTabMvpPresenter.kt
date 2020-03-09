package ru.shcherbakovdv.ss.trainee.ui.search.charities

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

@InjectViewState
class CharitiesTabMvpPresenter : ReactiveMvpPresenter<CharityTabMvpView>() {

    init {
        SearchFieldNotifier.searchField
                .flatMap { key -> CharitiesProvider.requestCharities(key).toObservable() }
                .subscribe { array -> viewState.setContent(array) }
                .let { attachDisposable(it) }
    }

}