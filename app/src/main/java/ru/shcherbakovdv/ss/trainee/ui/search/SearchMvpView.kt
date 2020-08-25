package ru.shcherbakovdv.ss.trainee.ui.search

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchMvpView : MvpView {

    fun setPage(position: Int)
}