package ru.shcherbakovdv.ss.trainee.mainscreen.profile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val userProfile = ProfileModel.userProfile
        if (userProfile != null) {
            viewState.fillProfileScreen(userProfile)
        }
    }
}