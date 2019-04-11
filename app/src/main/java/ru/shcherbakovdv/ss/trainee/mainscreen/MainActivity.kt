package ru.shcherbakovdv.ss.trainee.mainscreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakovdv.ss.trainee.NetworkCallback
import ru.shcherbakovdv.ss.trainee.OnNetworkChangeListener
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory.CategoryFragment
import ru.shcherbakovdv.ss.trainee.mainscreen.profile.ProfileFragment
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFragment

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface, OnNetworkChangeListener {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

    val networkCallback = NetworkCallback(this)

    private fun showFragmentPlaceholder() {
        supportFragmentManager.apply {
            findFragmentByTag(CategoryFragment.TAG).apply {
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

    override fun selectScreen(id: Int) {
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        textToolbarTitle.visibility = View.VISIBLE
        layoutToolbarSearch.visibility = View.GONE

        //Проверка на интернет, если его нет - загружать всегда фрагмент отказа интернета

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
                        textToolbarTitle.visibility = View.GONE
                        layoutToolbarSearch.visibility = View.VISIBLE
                        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                                .showSoftInput(edittextToolbarSearch, InputMethodManager.SHOW_FORCED)
                        edittextToolbarSearch.requestFocus()
                        menu.clear()
                        true
                    }
                }
            }
            R.id.bottom_help -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, CategoryFragment.newInstance(), CategoryFragment.TAG)
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
            else ->supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, ConnectionLostFragment.newInstance(getString(R.string.undefined_error)), ConnectionLostFragment.TAG)
                    .commit()
        }
    }

    override fun connectionAvailable() {
        Completable.complete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    selectScreen(bottomNavBar.selectedItemId)
                    bottomNavBar.visibility = View.VISIBLE
                    buttonHeart.visibility = View.VISIBLE
                }
    }

    override fun connectionLost() {
        Completable.complete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    textToolbarTitle.text = getString(R.string.title_error)
                    toolbar.menu.clear()
                    toolbar.navigationIcon = null
                    textToolbarTitle.visibility = View.VISIBLE
                    layoutToolbarSearch.visibility = View.GONE
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, ConnectionLostFragment.newInstance(getString(R.string.help_error)), ConnectionLostFragment.TAG)
                            .commit()
                    bottomNavBar.visibility = View.GONE
                    buttonHeart.visibility = View.GONE
                }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittextToolbarSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId){
                EditorInfo.IME_ACTION_SEARCH, EditorInfo.IME_ACTION_DONE -> {
                    val key = textView.text.toString()
                    SearchFieldNotifier.findContent(key)
                    true
                }
                else -> false
            }
        }

        bottomNavBar.enableAnimation(false)

        bottomNavBar.selectedItemId = presenter.currentScreenID
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
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        val storageAvailable = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if ( isConnected && storageAvailable) {
            connectionAvailable()
        } else {
            if (!storageAvailable){
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
            }
            connectionLost()
        }
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
    }

    override fun onStop() {
        super.onStop()
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_REQUEST_CODE -> connectionAvailable()
        }
    }

    companion object {
        const val STORAGE_REQUEST_CODE = 0
    }
}