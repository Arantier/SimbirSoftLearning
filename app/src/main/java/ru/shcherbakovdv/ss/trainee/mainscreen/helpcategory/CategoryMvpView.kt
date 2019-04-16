package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.dataclasses.Category

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryMvpView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    fun updateList(categories: Array<Category>)

    fun setErrorState()

}