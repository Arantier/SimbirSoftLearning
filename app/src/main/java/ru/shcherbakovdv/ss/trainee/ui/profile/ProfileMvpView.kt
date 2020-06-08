package ru.shcherbakovdv.ss.trainee.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.shcherbakovdv.ss.trainee.data.Profile

@AddToEndSingle
interface ProfileMvpView : MvpView {

    fun fillProfileScreen(profile: Profile)

    fun proceedToLoginScreen()
}