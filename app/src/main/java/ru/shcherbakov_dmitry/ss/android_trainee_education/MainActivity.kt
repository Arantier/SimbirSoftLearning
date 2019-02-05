package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.HelpScreen.HelpFragment
import ru.shcherbakov_dmitry.ss.android_trainee_education.ProfileScreen.ProfileFragment
import ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.SearchFragment

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface {

    @InjectPresenter lateinit var presenter: MainMvpPresenter

    private lateinit var helpFragment: HelpFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var searchFragment: SearchFragment

    override fun selectScreen(id: Int) {
        toolbar.menu.clear()
        toolbar.navigationIcon = null
        text_toolbar_title.visibility = View.VISIBLE
        layout_toolbar_search.visibility = View.GONE

        when (id) {
            R.id.bottom_news -> {
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,newsFragment)
//                        .commit()
                supportFragmentManager.beginTransaction()
                        .remove(helpFragment)
                        .remove(profileFragment)
                        .remove(searchFragment)
                        .commit()
                text_toolbar_title.text = "Unused"
            }
            R.id.bottom_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, searchFragment)
                        .commit()
                text_toolbar_title.text = getString(R.string.title_search)
                toolbar.inflateMenu(R.menu.search_toolbar)
                toolbar.menu.getItem(0).setOnMenuItemClickListener { item ->
                    text_toolbar_title.visibility = View.GONE
                    layout_toolbar_search.visibility = View.VISIBLE
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .showSoftInput(edit_toolbar_search,InputMethodManager.SHOW_FORCED)
                    edit_toolbar_search.requestFocus()
                    toolbar.menu.clear()
                    true
                }
            }
            R.id.bottom_help -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, helpFragment)
                        .commit()
                text_toolbar_title.text = getText(R.string.title_help)
            }
            R.id.bottom_history -> {
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,historyFragment)
//                        .commit()
                supportFragmentManager.beginTransaction()
                        .remove(helpFragment)
                        .remove(profileFragment)
                        .remove(searchFragment)
                        .commit()
                text_toolbar_title.text = "Unused"
            }
            R.id.bottom_profile -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit()
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
        image_toolbar_search.setOnClickListener{
            presenter.findContent(edit_toolbar_search.text as String?)
        }
        edit_toolbar_search.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                text_toolbar_title.visibility = View.VISIBLE
                view.visibility = View.GONE
                toolbar.inflateMenu(R.menu.search_toolbar)
            }
        }
        edit_toolbar_search.setOnEditorActionListener { editText, actionId, event->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                presenter.findContent(editText.text as String?)
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

        searchFragment = SearchFragment.newInstance()
        helpFragment = HelpFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
    }
}
