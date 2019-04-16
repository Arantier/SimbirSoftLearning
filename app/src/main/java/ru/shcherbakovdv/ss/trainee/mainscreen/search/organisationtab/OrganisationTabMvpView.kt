package ru.shcherbakovdv.ss.trainee.mainscreen.search.organisationtab

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.Organisation

interface OrganisationTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContent(organisationArray: Array<Organisation>)

}