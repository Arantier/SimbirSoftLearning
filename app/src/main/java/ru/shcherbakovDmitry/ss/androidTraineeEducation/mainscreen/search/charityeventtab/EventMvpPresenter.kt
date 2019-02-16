package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFieldObserver

@InjectViewState
class EventMvpPresenter : MvpPresenter<EventTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        SearchFieldNotifier.detachObserver(this)
    }

    override fun requestContent() {
        val eventArray = EventTabInteractor.requestEvents()
        viewState.setContent(eventArray)
    }

}