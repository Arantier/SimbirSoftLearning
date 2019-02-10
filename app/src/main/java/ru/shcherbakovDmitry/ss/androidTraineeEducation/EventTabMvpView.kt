package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface EventTabMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun setContent(eventArray:Array<CharityEvent>)

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun startCharityEventActivity(eventArray:Array<CharityEvent>)
}