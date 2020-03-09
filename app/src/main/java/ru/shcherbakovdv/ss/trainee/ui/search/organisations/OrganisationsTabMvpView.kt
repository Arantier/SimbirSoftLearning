package ru.shcherbakovdv.ss.trainee.ui.search.organisations

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Organisation

interface OrganisationsTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContent(organisations: Array<Organisation>)

}