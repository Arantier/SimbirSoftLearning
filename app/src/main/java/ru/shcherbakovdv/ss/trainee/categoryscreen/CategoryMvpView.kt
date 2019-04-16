package ru.shcherbakovdv.ss.trainee.categoryscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryMvpView : MvpView{

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    fun updateList(events: Array<CharityEvent>)

    fun setErrorState()
}