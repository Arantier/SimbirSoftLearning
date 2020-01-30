package ru.shcherbakovdv.ss.trainee.ui.search.charities

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Charity

interface CharityTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(charityArray:Array<Charity>)
}