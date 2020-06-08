package ru.shcherbakovdv.ss.trainee

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


@AddToEndSingle
interface MainMvpView : MvpView {

    fun selectScreen(id: Int)

    fun showSearchBar()

    fun setDisconnectedState()

    fun setConnectedState()
}