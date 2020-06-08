package ru.shcherbakovdv.ss.trainee.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.LoginPageActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Profile
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class ProfileFragment private constructor(val loginResponce: PublishSubject<Boolean>) :
    MvpAppCompatFragment(R.layout.fragment_profile_screen), ProfileMvpView {

    private val presenter by moxyPresenter { ProfilePresenter() }

    override fun fillProfileScreen(profile: Profile) {
        loginResponce.onNext(true)
        ImageProvider.loadImage(profile.pictureUrl, imageUserScreenPhoto)
        imageUserScreenPhoto.setOnClickListener {
            fragmentManager?.let { it1 ->
                EditPhotoDialog().show(it1, EditPhotoDialog.TAG)
            } ?: Logger.flatError("Fragment manager is null")
        }
        textUserScreenName.text = profile.name
        textUserScreenBirth.text = profile.birthDate
        textUserScreenBusiness.text = profile.business
        // TODO: Переделай
//        recyclerviewUserScreenFriends.adapter = FriendsListAdapter(profile.friends)
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            loginResponce.onNext(false)
        }
    }

    override fun proceedToLoginScreen() {
        startActivityForResult(Intent(context, LoginPageActivity::class.java), LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_CODE) {
            if (resultCode == LOGIN_SUCCESS) {
                presenter.requestUser()
                loginResponce.onNext(true)
            } else {
                loginResponce.onNext(false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_profile_screen, container, false)
        .also {
            presenter.requestUser()
            loginResponce.doOnNext {
                if (!it) {
                    content.makeGone()
                    progressBar.makeVisible()
                } else {
                    progressBar.makeGone()
                    content.makeVisible()
                }
            }
        }

    companion object {

        val LOGIN_CODE = 1
        val LOGIN_SUCCESS = 11
        val LOGIN_FAILURE = 12

        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance(loginResponce: PublishSubject<Boolean>) = ProfileFragment(loginResponce)
    }
}
