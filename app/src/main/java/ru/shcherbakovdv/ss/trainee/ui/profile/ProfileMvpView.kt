package ru.shcherbakovdv.ss.trainee.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data.Profile

interface ProfileMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillProfileScreen(profile: Profile)
}