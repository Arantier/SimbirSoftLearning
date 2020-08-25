package ru.shcherbakovdv.ss.trainee

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


interface MainMvpView : MvpView {

    @AddToEndSingle
    fun selectScreen(id: Int)

    @AddToEndSingle
    fun showSearchBar()

    @AddToEndSingle
    fun setDisconnectedState()

    @AddToEndSingle
    fun setConnectedState()
}