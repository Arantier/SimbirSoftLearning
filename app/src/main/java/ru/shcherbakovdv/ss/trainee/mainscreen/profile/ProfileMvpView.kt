package ru.shcherbakovdv.ss.trainee.mainscreen.profile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.mainscreen.dataclasses.UserProfile

interface ProfileMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillProfileScreen(profile: UserProfile)
}