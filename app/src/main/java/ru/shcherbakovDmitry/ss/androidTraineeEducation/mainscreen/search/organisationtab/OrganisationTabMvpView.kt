package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.Organisation

interface OrganisationTabMvpView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(organisationArray : Array<Organisation>)

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun startOrganisationActivity(organisationArray : Array<Organisation>)
}