package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileMvpView>() {

    fun requestUserProfile() {
        val userProfile = ProfileModel.userProfile
        if (userProfile != null) {
            viewState.fillProfileScreen(userProfile)
        } else {
            viewState.showLoginScreen()
        }
    }

    fun findAndShowProfile(profile: UserProfile) {
        //TODO:Потом
    }

    fun userLogout(){

    }
}