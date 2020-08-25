package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.widget.doOnTextChanged
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_login.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.ui.profile.ProfileFragment
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class LoginPageActivity : MvpAppCompatActivity(), LoginPageMvpView {

    val presenter by moxyPresenter { LoginPagePresenter() }
    val compositeDisposable = CompositeDisposable()

    override fun setLoadingState() {
        content.makeGone()
        progressBar.makeVisible()
    }

    override fun setLoginState() {
        progressBar.makeGone()
        content.makeVisible()
    }

    override fun loginResponse(isUserLoggedIn: Boolean) {
        if (isUserLoggedIn) {
            setResult(ProfileFragment.LOGIN_SUCCESS)
            finish()
        } else {
            Toast.makeText(this, "Неверная почта или пароль!", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toolbar.apply {
            setNavigationIcon(R.drawable.btn_back)
            setNavigationOnClickListener { onBackPressed() }
        }
        togglePasswordVisibility.setOnClickListener {
            editTextPassword.transformationMethod =
                if ((it as ToggleButton).isChecked) null else PasswordTransformationMethod()
        }
        val emailFieldObservable = Observable.create<Boolean> { emitter ->
            editTextEmail.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(!text.isNullOrEmpty())
            }
        }
        val passwordFieldObservable = Observable.create<Boolean> { emitter ->
            editTextPassword.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(!text.isNullOrEmpty())
            }
        }
        Observables.combineLatest(
            emailFieldObservable,
            passwordFieldObservable
        ) { emailNotEmpty, passwordNotEmpty -> emailNotEmpty and passwordNotEmpty }
            .subscribe { buttonLogin.isEnabled = it }
            .let(compositeDisposable::add)
        buttonLogin.setOnClickListener {
            presenter.requestToken(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
            )
        }
        editTextPassword.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                buttonLogin.apply { if (isEnabled) callOnClick() }
                true
            } else false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(ProfileFragment.LOGIN_FAILURE)
    }
}