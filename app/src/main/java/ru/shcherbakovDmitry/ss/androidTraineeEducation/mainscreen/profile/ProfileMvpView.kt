package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile

interface ProfileMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillProfileScreen(profile: UserProfile)
}