package ru.shcherbakovdv.ss.trainee

import com.google.firebase.auth.FirebaseAuth
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter

class LoginPagePresenter : ReactiveMvpPresenter<LoginPageMvpView>() {

    fun requestToken(email: String, password: String) {
        viewState.setLoadingState()
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                viewState.loginResponse(it.isSuccessful)
                viewState.setLoginState()
            }
    }
}