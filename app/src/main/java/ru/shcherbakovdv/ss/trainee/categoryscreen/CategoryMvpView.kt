package ru.shcherbakovdv.ss.trainee.categoryscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

interface CategoryMvpView : MvpView{

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateList(events: Array<CharityEvent>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setErrorState()
}