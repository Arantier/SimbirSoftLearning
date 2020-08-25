package ru.shcherbakovdv.ss.trainee

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.OrganisationsProvider

class CharityPagePresenter : ReactiveMvpPresenter<CharityPageMvpView>() {

    fun prepareScreen(charity: Charity) {
        OrganisationsProvider.getOrganisationById(charity.organisationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ organisation ->
                viewState.setCharityScreen(charity, organisation)
            }, viewState::setErrorState)
            .let(this::attachDisposable)
    }

}