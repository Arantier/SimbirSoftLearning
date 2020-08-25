package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.OrganisationsProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

class OrganisationsTabPresenter : ReactiveMvpPresenter<OrganisationsTabMvpView>() {

    init {
        SearchFieldNotifier.searchField
            .subscribe {
                OrganisationsProvider.find(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewState::setContent)
            }.let(this::attachDisposable)
    }
}