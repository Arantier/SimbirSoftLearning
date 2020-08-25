package ru.shcherbakovdv.ss.trainee.ui.profile

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import ru.shcherbakovdv.ss.trainee.data.User
import ru.shcherbakovdv.ss.trainee.data.providers.UsersProvider

class ProfilePresenter : MvpPresenter<ProfileMvpView>() {

    val auth = FirebaseAuth.getInstance()

    fun getFriendsVisuals(user: User) =
        user.friends?.let {
            it.map { url ->
                UsersProvider.getUserVisuals(url)
                    .filter { it.first.isNotEmpty() and it.second.isNotEmpty() }
                    .toFlowable()
            }.reduce { acc, flowable -> acc.mergeWith(flowable) }
                .toList()
                .map { it.toTypedArray() }
        } ?: Single.just(emptyArray())

    fun requestUser() {
        auth.currentUser?.let { firebaseUser ->
            UsersProvider.getUserById(firebaseUser.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    viewState::fillProfileScreen,
                    viewState::setErrorState
                )
        } ?: viewState.proceedToLoginScreen()
    }
}