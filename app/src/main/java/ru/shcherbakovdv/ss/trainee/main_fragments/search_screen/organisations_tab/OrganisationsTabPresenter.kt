package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.organisations_tab

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.OrganisationsProvider
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.SearchFieldObserver

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