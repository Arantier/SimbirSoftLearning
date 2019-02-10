package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile

import com.arellomobile.mvp.MvpView
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile

interface ProfileMvpView : MvpView {

    fun fillProfileScreen(profile: UserProfile)

    fun showLoginScreen()
}