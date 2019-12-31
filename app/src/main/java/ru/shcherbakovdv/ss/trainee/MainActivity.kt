package ru.shcherbakovdv.ss.trainee

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakovdv.ss.trainee.main_fragments.CategoryTypesFragment
import ru.shcherbakovdv.ss.trainee.main_fragments.ConnectionLostFragment
import ru.shcherbakovdv.ss.trainee.main_fragments.ProfileFragment
import ru.shcherbakovdv.ss.trainee.main_fragments.SearchFragment
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class MainActivity : MvpAppCompatActivity(), MainMvpView {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

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
        selectScreen(bottomNavBar.selectedItemId)
        bottomNavBar.makeVisible()
        buttonHeart.makeVisible()
    }

    override fun setUnconnectedState() {
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

    override fun requestPermissions(name: String, code: Int) {
        ActivityCompat.requestPermissions(this,
                arrayOf(name), code)
    }

    override fun selectScreen(id: Int) {
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        textToolbarTitle.makeVisible()
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
                        textToolbarTitle.makeGone()
                        layoutToolbarSearch.makeVisible()
                        menu.clear()
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

        bottomNavBar.enableAnimation(false)

        bottomNavBar.selectedItemId = R.id.bottom_help
        bottomNavBar.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            if (item.isChecked) {
                false
            } else {
                presenter.currentScreenID = item.itemId
                true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.observeNetwork(this)

    }

    override fun onStop() {
        super.onStop()
        presenter.disposeNetwork(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                presenter.storageAvailable = (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            }
        }
    }

    companion object {
        const val STORAGE_REQUEST_CODE = 0
    }
}