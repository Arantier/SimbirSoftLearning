package ru.shcherbakovdv.ss.trainee

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainMvpView : MvpView {

    fun selectScreen(id: Int)

    fun setUnconnectedState()

    fun setConnectedState()

    fun requestPermissions(name:String, code: Int)
}