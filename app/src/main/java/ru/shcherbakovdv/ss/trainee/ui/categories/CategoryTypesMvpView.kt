package ru.shcherbakovdv.ss.trainee.ui.categories

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
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