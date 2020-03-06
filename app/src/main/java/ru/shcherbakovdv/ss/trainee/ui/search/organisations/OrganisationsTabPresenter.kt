package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.OrganisationsProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldObserver

@InjectViewState
class OrganisationsTabPresenter : MvpPresenter<OrganisationsTabMvpView>(), SearchFieldObserver {

    init {
        SearchFieldNotifier.attachObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SearchFieldNotifier.detachObserver(this)
    }

    override fun requestContent(key:String?) {
        val organisationArray = OrganisationsProvider.requestOrganisations()
        viewState.setContent(organisationArray)
    }
}