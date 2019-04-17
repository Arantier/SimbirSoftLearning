package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data_classes.Charity

interface CharityTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(charityArray:Array<Charity>)
}