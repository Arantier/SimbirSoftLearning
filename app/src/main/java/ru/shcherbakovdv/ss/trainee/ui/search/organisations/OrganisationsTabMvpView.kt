package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Organisation

interface OrganisationsTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContent(organisations: Array<Organisation>)

}