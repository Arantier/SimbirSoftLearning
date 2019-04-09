package ru.shcherbakovdv.ss.trainee.mainscreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory.CategoryFragment
import ru.shcherbakovdv.ss.trainee.mainscreen.profile.ProfileFragment
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFieldNotifier
import ru.shcherbakovdv.ss.trainee.mainscreen.search.SearchFragment

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

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
        bottomNavBar.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            if (item.isChecked) {
                false
            } else {
                presenter.currentScreenID = item.itemId
                true
            }
        }
        bottomNavBar.selectedItemId = presenter.currentScreenID

        //TODO: тупо код для пермишнов, юзабельности с точки зрения проекта ноль, но для отладки обязателен
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }
    }
}