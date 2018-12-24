package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_help_categories.*

class HelpCategoriesActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    private lateinit var helpCategoryFragment: HelpCategoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_categories)
        toolbar = toolbar_help
        helpCategoryFragment = HelpCategoryFragment.newInstance()

        toolbar.setNavigationOnClickListener { view ->
            Toast.makeText(view.context, "Back", Toast.LENGTH_SHORT)
                    .show()
        }

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(fragment_container.id,helpCategoryFragment)
        ft.commit()
    }
}
