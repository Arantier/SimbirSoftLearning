package ru.shcherbakovdv.ss.trainee.ui.categories

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Category

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryTypesMvpView : MvpView {

    @StateStrategyType(SingleStateStrategy::class)
    fun setLoadingState()

    @StateStrategyType(SingleStateStrategy::class)
    fun updateList(categories: Array<Category>)

    @StateStrategyType(SingleStateStrategy::class)
    fun setErrorState()

}