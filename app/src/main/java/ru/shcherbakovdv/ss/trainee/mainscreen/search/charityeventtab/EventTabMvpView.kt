package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

interface EventTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(eventArray:Array<CharityEvent>)
}