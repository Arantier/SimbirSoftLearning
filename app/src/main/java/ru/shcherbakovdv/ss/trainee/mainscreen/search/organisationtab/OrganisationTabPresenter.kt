package ru.shcherbakovdv.ss.trainee.mainscreen.search.organisationtab

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldObserver

@InjectViewState
class OrganisationTabPresenter : MvpPresenter<OrganisationTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchFieldNotifier.detachObserver(this)
    }

    override fun requestContent(key:String?) {
        val organisationArray = OrganisationTabInteractor.requestOrganisations()
        viewState.setContent(organisationArray)
    }
}