package ru.shcherbakovdv.ss.trainee.ui.search.charities

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldObserver

@InjectViewState
class CharitiesTabMvpPresenter : ReactiveMvpPresenter<CharityTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() = SearchFieldNotifier.detachObserver(this)

    override fun requestContent(key: String?) {
        CharitiesProvider.requestCharities(key ?: "")
                .subscribe { array -> viewState.setContent(array) }
                .let { attachDisposable(it) }
    }

}