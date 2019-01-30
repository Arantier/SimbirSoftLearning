package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.HelpScreen.HelpScreen
import ru.shcherbakov_dmitry.ss.android_trainee_education.ProfileScreen.ProfileScreen

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface {

    @InjectPresenter
    lateinit var presenter: MainMvpPresenter

    private lateinit var helpScreen: HelpScreen
    private lateinit var profileScreen: ProfileScreen

    override fun selectScreen(id: Int) {
        when (id){
            R.id.bottom_news ->{
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,newsFragment)
//                        .commit()
                supportFragmentManager.beginTransaction()
                        .remove(helpScreen)
                        .remove(profileScreen)
                        .commit()
                text_toolbar_title.text = "Unused"
            }
            R.id.bottom_search ->{
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,searchFragment)
//                        .commit()
                supportFragmentManager.beginTransaction()
                        .remove(helpScreen)
                        .remove(profileScreen)
                        .commit()
            }
            R.id.bottom_help ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,helpScreen)
                        .commit()
                text_toolbar_title.text = getText(R.string.title_help)
            }
            R.id.bottom_history ->{
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,historyFragment)
//                        .commit()
                supportFragmentManager.beginTransaction()
                        .remove(helpScreen)
                        .remove(profileScreen)
                        .commit()
                text_toolbar_title.text = "Unused"
            }
            R.id.bottom_profile ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,profileScreen)
                        .commit()
                text_toolbar_title.text = getText(R.string.title_profile)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        helpScreen = HelpScreen.newInstance()
        profileScreen = ProfileScreen.newInstance()
    }
}
