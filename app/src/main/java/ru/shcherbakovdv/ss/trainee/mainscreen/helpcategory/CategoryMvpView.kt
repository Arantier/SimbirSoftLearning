package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.Category

interface CategoryMvpView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateList(categories: Array<Category>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setErrorState()

}