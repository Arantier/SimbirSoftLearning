package ru.shcherbakovdv.ss.trainee.ui.search.charities

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Charity

interface CharityTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(charityArray:Array<Charity>)
}