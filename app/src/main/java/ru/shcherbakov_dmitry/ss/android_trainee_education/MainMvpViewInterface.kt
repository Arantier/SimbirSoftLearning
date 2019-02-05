package ru.shcherbakov_dmitry.ss.android_trainee_education

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy :: class)
interface MainMvpViewInterface : MvpView {

    fun selectScreen(id: Int)

}