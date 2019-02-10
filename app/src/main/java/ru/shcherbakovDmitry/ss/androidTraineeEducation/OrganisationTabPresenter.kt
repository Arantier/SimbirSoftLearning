package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class OrganisationTabPresenter : MvpPresenter<OrganisationTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchFieldNotifier.detachObserver(this)
    }

    fun requestContent() = requestContent(null)

    override fun requestContent(key: String?) {
        val organisationArray = OrganisationTabInteractor.requestOrganisations(key)
        viewState.setContent(organisationArray)
    }

    fun requestOrganisationActivity(organisationId: Int) {
    }
}