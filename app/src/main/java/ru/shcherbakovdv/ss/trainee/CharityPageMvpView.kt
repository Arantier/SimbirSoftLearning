package ru.shcherbakovdv.ss.trainee

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.Organisation

@AddToEndSingle
interface CharityPageMvpView : MvpView {

    fun setCharityScreen(charity: Charity, organisation: Organisation)

    fun setErrorScreen(t: Throwable)

    fun setLoadingScreen()
}