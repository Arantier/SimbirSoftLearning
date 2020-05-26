package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import moxy.InjectViewState
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.OrganisationsProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

@InjectViewState
class OrganisationsTabPresenter : ReactiveMvpPresenter<OrganisationsTabMvpView>() {

    init {
        SearchFieldNotifier.searchField
                .map { OrganisationsProvider.requestOrganisations() }
                .subscribe { organisations -> viewState.setContent(organisations) }
                .let { attachDisposable(it) }
    }
}