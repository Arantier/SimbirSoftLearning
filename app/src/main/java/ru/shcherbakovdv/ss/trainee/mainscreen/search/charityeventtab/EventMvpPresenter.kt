package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.dataclasses.EventProvider
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldObserver

@InjectViewState
class EventMvpPresenter : MvpPresenter<EventTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        SearchFieldNotifier.detachObserver(this)
    }

    override fun requestContent(key: String?) {
        EventProvider.requestEvents(key ?: "")
                .subscribe { array -> viewState.setContent(array) }
    }

}