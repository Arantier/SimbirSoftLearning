package ru.shcherbakovdv.ss.trainee.ui.profile

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.ProfileModel

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val userProfile = ProfileModel.profile
        if (userProfile != null) {
            viewState.fillProfileScreen(userProfile)
        }
    }
}