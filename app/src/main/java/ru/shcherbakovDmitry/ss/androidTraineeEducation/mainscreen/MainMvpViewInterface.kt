package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainMvpViewInterface : MvpView {

    @StateStrategyType(AddToEndSingleStrategy :: class)
    fun selectScreen(id: Int)

}