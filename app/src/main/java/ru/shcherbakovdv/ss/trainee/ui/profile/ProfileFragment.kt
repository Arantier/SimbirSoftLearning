package ru.shcherbakovdv.ss.trainee.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.fragment_profile_screen.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.LoginPageActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.User
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class ProfileFragment private constructor(val loginResponce: PublishSubject<Boolean>) :
    MvpAppCompatFragment(R.layout.fragment_profile_screen), ProfileMvpView {

    private val presenter by moxyPresenter { ProfilePresenter() }

    var friendsLoadDisposable: Disposable? = null

    override fun fillProfileScreen(user: User) {
        ImageProvider.loadImage(user.photoUrl, imageUserScreenPhoto)
        imageUserScreenPhoto.setOnClickListener {
            fragmentManager?.let { it1 ->
                EditPhotoDialog().show(it1, EditPhotoDialog.TAG)
            } ?: Logger.flatError("Fragment manager is null")
        }
        textUserScreenName.text = user.name
        textUserScreenBirth.text = user.birthDate
        textUserScreenBusiness.text = user.business
        friendsLoadDisposable = presenter.getFriendsVisuals(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.size != 0) {
                    recyclerviewUserScreenFriends.adapter = FriendsListAdapter(it)
                    friendsProgressBar.makeGone()
                    textFriendsMessage.makeGone()
                    recyclerviewUserScreenFriends.makeVisible()
                } else {
                    friendsProgressBar.makeGone()
                    recyclerviewUserScreenFriends.makeGone()
                    textFriendsMessage.apply {
                        text = getString(R.string.profile_no_friends)
                        context?.let { setTextColor(it.resources.getColor(R.color.textColorPrimary)) }
                        makeVisible()
                    }
                }
            }, {
                friendsProgressBar.makeGone()
                recyclerviewUserScreenFriends.makeGone()
                textFriendsMessage.apply {
                    text = getString(R.string.profile_friends_error)
                    context?.let { setTextColor(it.resources.getColor(R.color.textColorError)) }
                    makeVisible()
                }
            })

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            loginResponce.onNext(false)
        }
        progressBar.makeGone()
        content.makeVisible()
    }

    override fun setErrorState(throwable: Throwable) {
        content.makeGone()
        progressBar.makeGone()
        errorScreen.makeVisible()
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
            it.recyclerviewUserScreenFriends.layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically() = false
            }
            presenter.requestUser()
        }

    override fun onDestroy() {
        super.onDestroy()
        friendsLoadDisposable?.dispose()
    }

    companion object {

        val LOGIN_CODE = 1
        val LOGIN_SUCCESS = 11
        val LOGIN_FAILURE = 12

        @JvmStatic
        val TAG = ProfileFragment::class.simpleName

        @JvmStatic
        fun newInstance(loginResponse: PublishSubject<Boolean>) = ProfileFragment(loginResponse)
    }
}
