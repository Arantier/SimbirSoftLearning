package ru.shcherbakovdv.ss.trainee

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Charity

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryPageMvpView : MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun setLoadingState()

    @StateStrategyType(SingleStateStrategy::class)
    fun updateList(events: Array<Charity>)

    @StateStrategyType(SingleStateStrategy::class)
    fun setErrorState()
}