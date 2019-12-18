package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab

import com.arellomobile.mvp.InjectViewState
import ru.shcherbakovdv.ss.trainee.project_classes.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.EventsProvider
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.SearchFieldObserver

@InjectViewState
class CharitiesTabMvpPresenter : ReactiveMvpPresenter<CharityTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() = SearchFieldNotifier.detachObserver(this)

    override fun requestContent(key: String?) {
        EventsProvider.requestEvents(key ?: "")
                .subscribe { array -> viewState.setContent(array) }
                .let { attachDisposable(it) }
    }

}