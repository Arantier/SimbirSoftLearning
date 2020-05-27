package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import ru.shcherbakovdv.ss.trainee.ui.CategoryTypesFragment
import ru.shcherbakovdv.ss.trainee.ui.ConnectionLostFragment
import ru.shcherbakovdv.ss.trainee.ui.profile.ProfileFragment
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFragment
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainMvpView {

    private val presenter by moxyPresenter { MainMvpPresenter() }

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

    override fun setConnectedState() {
        selectScreen(bottomNavBar.id)
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
                .replace(R.id.fragmentContainer, ConnectionLostFragment.newInstance(getString(R.string.help_error)), ConnectionLostFragment.TAG)
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
            R.id.bottom_news -> {
                showFragmentPlaceholder()
            }
            R.id.bottom_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, SearchFragment.newInstance(), SearchFragment.TAG)
                        .commit()
                textToolbarTitle.text = getString(R.string.title_search)
                toolbar.apply {
                    inflateMenu(R.menu.search_toolbar)
                    menu.getItem(0).setOnMenuItemClickListener { item ->
                        presenter.prepareForSearch()
                        true
                    }
                }
            }
            R.id.bottom_help -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CategoryTypesFragment.newInstance(), CategoryTypesFragment.TAG)
                        .commit()
                textToolbarTitle.text = getText(R.string.title_help)
            }
            R.id.bottom_history -> {
                showFragmentPlaceholder()
            }
            R.id.bottom_profile -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ProfileFragment.newInstance(), ProfileFragment.TAG)
                        .commit()
                toolbar.apply {
                    inflateMenu(R.menu.profile_toolbar)
                }
                textToolbarTitle.text = getText(R.string.title_profile)
            }
            else -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ConnectionLostFragment.newInstance(getString(R.string.undefined_error)), ConnectionLostFragment.TAG)
                    .commit()
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

        bottomNavBar.clearAnimation()

//        bottomNavBar.selectedItemId = R.id.bottom_help
//        bottomNavBar.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            if (item.isChecked) {
//                false
//            } else {
//                presenter.currentScreenID = item.itemId
//                true
//            }
//        }
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