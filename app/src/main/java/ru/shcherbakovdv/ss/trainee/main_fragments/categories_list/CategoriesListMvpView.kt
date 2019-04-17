package ru.shcherbakovdv.ss.trainee.main_fragments.categories_list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data_classes.Category

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesListMvpView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLoadingState()

    fun updateList(categories: Array<Category>)

    fun setErrorState()

}