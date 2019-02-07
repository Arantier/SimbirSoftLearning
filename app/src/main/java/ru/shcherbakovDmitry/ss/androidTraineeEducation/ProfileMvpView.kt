package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.MvpView

interface ProfileMvpView : MvpView {

    fun fillProfileScreen(profile: UserProfile)

    fun showLoginScreen()
}