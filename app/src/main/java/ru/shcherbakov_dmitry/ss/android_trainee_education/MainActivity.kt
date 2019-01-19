package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.HelpFragments.HelpFragment
import ru.shcherbakov_dmitry.ss.android_trainee_education.ProfileFragments.ProfileFragment

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface, ProfileFragment.ProfileInterface, HelpFragment.HelpInterface {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

    lateinit var toolbar: Toolbar
    private lateinit var bottomBar: BottomNavigationViewEx

    private lateinit var helpFragment: HelpFragment
    private lateinit var profileFragment: ProfileFragment

    override fun selectItem(item: MenuItem) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        when (item.itemId) {
            R.id.bottom_news ->{

            }
            R.id.bottom_search ->{

            }
            R.id.bottom_help ->{
                ft.replace(fragment_container.id, helpFragment)
            }
            R.id.bottom_history->{

            }
            R.id.bottom_profile->{
                ft.replace(fragment_container.id, profileFragment)
            }
        }
        ft.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = toolbar_help
        bottomBar = bottom_nav_bar
        bottomBar.enableAnimation(false)
        bottomBar.setOnNavigationItemSelectedListener { item -> presenter.itemSelected(item) }
        button_heart.setOnClickListener {
            bottomBar.currentItem = 2
        }
        bottomBar.currentItem = 2

        helpFragment = HelpFragment.newInstance(this)
        profileFragment = ProfileFragment.newInstance(this)
    }

    //************
    //Методы коллбека для ProfileFragment
    //************

    override fun onProfileShow() {
        text_toolbar_title.setText(R.string.title_profile)
        toolbar.inflateMenu(R.menu.menu_toolbar_profile_edit)
        toolbar.menu.getItem(0).setOnMenuItemClickListener {
            //TODO: Сюда добавить создание фрагмента эдита, добавление его в BackStack и прочее
            true
        }
    }

    override fun onProfileClose() {
        toolbar.menu.clear()
    }

    //************
    //Методы коллбека для HelpFragment
    //************

    override fun onHelpShow() {
        text_toolbar_title.setText(R.string.title_help)
    }

    override fun onHelpClose() {

    }
}
