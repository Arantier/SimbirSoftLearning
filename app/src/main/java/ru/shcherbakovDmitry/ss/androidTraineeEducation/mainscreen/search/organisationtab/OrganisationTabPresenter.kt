package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFieldObserver

@InjectViewState
class OrganisationTabPresenter : MvpPresenter<OrganisationTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchFieldNotifier.detachObserver(this)
    }

    override fun requestContent() {
        val organisationArray = OrganisationTabInteractor.requestOrganisations()
        viewState.setContent(organisationArray)
    }
}