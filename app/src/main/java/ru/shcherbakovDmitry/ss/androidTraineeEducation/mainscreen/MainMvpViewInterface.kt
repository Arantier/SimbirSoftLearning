package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy :: class)
interface MainMvpViewInterface : MvpView {

    fun selectScreen(id: Int)

}