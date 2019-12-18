package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.organisations_tab

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data_classes.Organisation

interface OrganisationsTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContent(organisationArray: Array<Organisation>)

}