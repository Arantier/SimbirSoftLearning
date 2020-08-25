package ru.shcherbakovdv.ss.trainee

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface LoginPageMvpView : MvpView {

    fun loginResponse(isLoggedIn: Boolean)

    fun setLoadingState()

    fun setLoginState()
}