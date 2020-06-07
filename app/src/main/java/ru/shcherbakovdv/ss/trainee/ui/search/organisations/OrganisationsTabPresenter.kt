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
                OrganisationsProvider.organisationsSingle
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewState::setContent) {
                        // TODO: пока нету вкладки организаций, нет смысла с этим возиться
                    }
            }.let(this::attachDisposable)
    }
}