package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.ui.ConnectionLostFragment
import ru.shcherbakovdv.ss.trainee.ui.categories.CategoryTypesFragment
import ru.shcherbakovdv.ss.trainee.ui.profile.ProfileFragment
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFragment
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible
import javax.security.auth.login.LoginException

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainMvpView {

    private val presenter by moxyPresenter { MainMvpPresenter() }

    private fun showNewsFragment() = showFragmentPlaceholder()

    private fun showSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                SearchFragment.newInstance(),
                SearchFragment.TAG
            )
            .commit()
        textToolbarTitle.text = getString(R.string.title_search)
        toolbar.apply {
            inflateMenu(R.menu.search_toolbar)
            menu.getItem(0).setOnMenuItemClickListener { item ->
                showSearchBar()
                true
            }
        }
    }

    private fun showHelpFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                CategoryTypesFragment.newInstance(),
                CategoryTypesFragment.TAG
            )
            .commit()
        textToolbarTitle.text = getText(R.string.title_help)
    }

    private fun showHistoryFragment() = showFragmentPlaceholder()

    private fun showProfileFragment() {
        val loginResponce = PublishSubject.create<Boolean>()
            .apply {
                flatMapCompletable {
                    if (it) Completable.complete()
                    else Completable.error(LoginException("User didn't log in."))
                }.subscribe({ }, {
                    bottomNavBar.selectedItemId = R.id.bottom_help
                    Toast.makeText(
                        this@MainActivity,
                        R.string.login_action_error_responce,
                        Toast.LENGTH_LONG
                    ).show()
                })
            }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                ProfileFragment.newInstance(loginResponce),
                ProfileFragment.TAG
            )
            .commit()
        toolbar.apply {
            inflateMenu(R.menu.profile_toolbar)
        }
        textToolbarTitle.text = getText(R.string.title_profile)
    }

    private fun showFragmentPlaceholder() {
        supportFragmentManager.apply {
            findFragmentByTag(CategoryTypesFragment.TAG).apply {
                if (this != null) {
                    beginTransaction().remove(this).commit()
                }
            }
            findFragmentByTag(ProfileFragment.TAG).apply {
                if (this != null) {
                    beginTransaction().remove(this).commit()
                }
            }
        }
        textToolbarTitle.text = "Unused"
    }

    private fun showErrorFragment(messageId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                ConnectionLostFragment.newInstance(getString(messageId)),
                ConnectionLostFragment.TAG
            )
            .commit()
    }

    private fun showErrorFragment(message: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                ConnectionLostFragment.newInstance(message),
                ConnectionLostFragment.TAG
            )
            .commit()
    }

    override fun setConnectedState() {
        selectScreen(bottomNavBar.selectedItemId)
        bottomNavBar.makeVisible()
        buttonHeart.makeVisible()
    }

    override fun setDisconnectedState() {
        textToolbarTitle.text = getString(R.string.title_error)
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        textToolbarTitle.makeVisible()
        layoutToolbarSearch.makeGone()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                ConnectionLostFragment.newInstance(getString(R.string.help_error)),
                ConnectionLostFragment.TAG
            )
            .commit()
        bottomNavBar.makeGone()
        buttonHeart.makeGone()
    }

    override fun selectScreen(id: Int) {
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        textToolbarTitle.makeVisible()
        edittextToolbarSearch.text.clear()
        layoutToolbarSearch.makeGone()

        when (id) {
            R.id.bottom_news -> showNewsFragment()
            R.id.bottom_search -> showSearchFragment()
            R.id.bottom_help -> showHelpFragment()
            R.id.bottom_history -> showHistoryFragment()
            R.id.bottom_profile -> showProfileFragment()
            else -> showErrorFragment(R.string.unexpected_error)
        }
    }

    override fun showSearchBar() {
        textToolbarTitle.makeGone()
        layoutToolbarSearch.makeVisible()
        toolbar.menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittextToolbarSearch.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH, EditorInfo.IME_ACTION_DONE -> {
                    val key = textView.text.toString()
                    presenter.findContent(key)
                    true
                }
                else -> false
            }
        }

        bottomNavBar.setOnNavigationItemSelectedListener { item ->
            if (item.isChecked) {
                false
            } else {
                presenter.currentScreenID = item.itemId
                true
            }
        }
        bottomNavBar.selectedItemId = R.id.bottom_help
    }


    override fun onStart() {
        super.onStart()
        presenter.observeNetwork(this)

    }

    override fun onStop() {
        super.onStop()
        presenter.disposeNetwork(this)
    }
}