package ru.shcherbakovdv.ss.trainee

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data_classes.Charity

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryMvpView : MvpView{

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    fun updateList(events: Array<Charity>)

    fun setErrorState()
}