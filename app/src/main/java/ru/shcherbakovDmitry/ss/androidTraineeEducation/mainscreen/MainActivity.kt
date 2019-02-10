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
        text_toolbar_title.text = "Unused"
    }

    override fun selectScreen(id: Int) {
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        text_toolbar_title.visibility = View.VISIBLE
        layout_toolbar_search.visibility = View.GONE

        when (id) {
            R.id.bottom_news -> {
                showFragmentPlaceholder()
            }
            R.id.bottom_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.newInstance(), SearchFragment.TAG)
                        .commit()
                text_toolbar_title.text = getString(R.string.title_search)
                toolbar.apply {
                    inflateMenu(R.menu.search_toolbar)
                    menu.getItem(0).setOnMenuItemClickListener { item ->
                        text_toolbar_title.visibility = View.GONE
                        layout_toolbar_search.visibility = View.VISIBLE
                        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                                .showSoftInput(edit_toolbar_search,InputMethodManager.SHOW_FORCED)
                        edit_toolbar_search.requestFocus()
                        menu.clear()
                        true
                    }
                }
            }
            R.id.bottom_help -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HelpFragment.newInstance(), HelpFragment.TAG)
                        .commit()
                text_toolbar_title.text = getText(R.string.title_help)
            }
            R.id.bottom_history -> {
                showFragmentPlaceholder()
            }
            R.id.bottom_profile -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment.newInstance(), ProfileFragment.TAG)
                        .commit()
                toolbar.apply {
                    inflateMenu(R.menu.profile_toolbar)
                    menu.getItem(0).setOnMenuItemClickListener {
                        //TODO: потом понадобится
                        true
                    }
                }
                text_toolbar_title.text = getText(R.string.title_profile)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_toolbar_mic.setOnClickListener {
            //TODO:Аналогично
        }
        image_toolbar_search.setOnClickListener {
            presenter.findContent(edit_toolbar_search.text as String?)
        }
        edit_toolbar_search.setOnEditorActionListener { editText, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.findContent(editText.text.toString())
                true
            } else {
                false
            }
        }

        bottom_nav_bar.enableAnimation(false)
        bottom_nav_bar.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            if (item.isChecked) {
                false
            } else {
                presenter.currentScreenID = item.itemId
                true
            }
        }
        bottom_nav_bar.selectedItemId = presenter.currentScreenID
    }
}
