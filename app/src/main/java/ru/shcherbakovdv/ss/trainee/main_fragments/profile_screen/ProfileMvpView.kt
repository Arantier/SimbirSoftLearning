package ru.shcherbakovdv.ss.trainee.main_fragments.profile_screen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakovdv.ss.trainee.data_classes.Profile

interface ProfileMvpView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun fillProfileScreen(profile: Profile)
}