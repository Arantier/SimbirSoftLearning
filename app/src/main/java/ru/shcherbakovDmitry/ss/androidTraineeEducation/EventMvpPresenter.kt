package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class EventMvpPresenter : MvpPresenter<EventTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        SearchFieldNotifier.detachObserver(this)
    }

    fun requestContent() = requestContent(null)

    override fun requestContent(key: String?) {
        val eventArray = EventTabInteractor.requestEvents(key)
        viewState.setContent(eventArray)
    }

    fun requestOrganisationActivity(organisationId: Int) {
    }

}