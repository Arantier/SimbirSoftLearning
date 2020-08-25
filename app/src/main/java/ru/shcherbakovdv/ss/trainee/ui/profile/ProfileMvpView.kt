package ru.shcherbakovdv.ss.trainee.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.shcherbakovdv.ss.trainee.data.User

@AddToEndSingle
interface ProfileMvpView : MvpView {

    fun fillProfileScreen(user: User)

    fun setErrorState(throwable: Throwable)

    fun proceedToLoginScreen()
}