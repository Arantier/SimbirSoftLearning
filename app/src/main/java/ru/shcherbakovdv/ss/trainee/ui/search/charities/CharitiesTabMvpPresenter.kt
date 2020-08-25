package ru.shcherbakovdv.ss.trainee.ui.search.charities

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

class CharitiesTabMvpPresenter : ReactiveMvpPresenter<CharityTabMvpView>() {

    init {
        SearchFieldNotifier.searchField
                .flatMapSingle { key ->
                    CharitiesProvider.find(key)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewState.setContent(it) }
                .let { attachDisposable(it) }
    }

}