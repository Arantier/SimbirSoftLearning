package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.UserProfile

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