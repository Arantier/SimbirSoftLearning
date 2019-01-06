package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainMvpViewInterface {

    @InjectPresenter
    lateinit var presenter : MainMvpPresenter

    lateinit var toolbar: Toolbar
    lateinit var bottomBar : BottomNavigationViewEx

    private lateinit var helpFragment: HelpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = toolbar_help
        bottomBar = bottom_nav_bar
        bottomBar.currentItem = 2
        bottomBar.enableAnimation(false)
        button_heart.setOnClickListener{
            bottomBar.currentItem = 2
        }

        helpFragment = HelpFragment.newInstance()

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(fragment_container.id,helpFragment)
        ft.commit()
    }
}
