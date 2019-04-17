package ru.shcherbakovdv.ss.trainee.main_fragments.profile_screen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data_providers.ProfileModel
import ru.shcherbakovdv.ss.trainee.main_fragments.profile_screen.ProfileMvpView

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