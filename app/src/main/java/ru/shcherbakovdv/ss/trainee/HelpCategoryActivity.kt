package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_category.*
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventListAdapter
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventTabInteractor

class HelpCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //TODO: УБРАТЬ!!!
        val events = EventTabInteractor.requestEvents("")
        recyclerView.adapter = EventListAdapter(events)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        when (intent.getIntExtra(CATEGORY_ID, -1)) {
            CATEGORY_KIDS -> {
                toolbarTitle.text = getString(R.string.category_kids)
            }
            CATEGORY_ADULTS -> {
                toolbarTitle.text = getString(R.string.category_adults)
            }
            CATEGORY_ELDERS -> {
                toolbarTitle.text = getString(R.string.category_elders)
            }
            CATEGORY_ANIMALS -> {
                toolbarTitle.text = getString(R.string.category_animals)
            }
            CATEGORY_EVENTS -> {
                toolbarTitle.text = getString(R.string.category_events)
            }
        }
        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.btn_back)
            setNavigationOnClickListener {
                finish()
            }
            inflateMenu(R.menu.category_toolbar)
        }
    }

    companion object {
        const val CATEGORY_KIDS = 0
        const val CATEGORY_ADULTS = 1
        const val CATEGORY_ELDERS = 2
        const val CATEGORY_ANIMALS = 3
        const val CATEGORY_EVENTS = 4

        const val CATEGORY_ID = "category_id"
    }

}
