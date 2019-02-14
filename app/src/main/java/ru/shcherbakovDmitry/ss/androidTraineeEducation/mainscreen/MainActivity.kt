package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.helpcategory.HelpFragment
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.profile.ProfileFragment
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFragment

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

    private fun showFragmentPlaceholder() {
        supportFragmentManager.apply {
            findFragmentByTag(HelpFragment.TAG).apply {
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
                                .showSoftInput(edittextToolbarSearch,InputMethodManager.SHOW_FORCED)
                        edittextToolbarSearch.requestFocus()
                        menu.clear()
                        true
                    }
                }
            }
            R.id.bottom_help -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HelpFragment.newInstance(), HelpFragment.TAG)
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
//TODO: Если не нужно - удаляй
//        imageToolbarSearch.setOnClickListener {
//            presenter.findContent(edittextToolbarSearch.text as String?)
//        }
//        edittextToolbarSearch.setOnEditorActionListener { editText, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                presenter.findContent(editText.text.toString())
//                true
//            } else {
//                false
//            }
//        }

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
    }
}