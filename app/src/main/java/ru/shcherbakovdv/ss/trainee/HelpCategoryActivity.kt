package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_category.*
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventListAdapter
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventTabInteractor
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.OnCharityEventClickListener

class HelpCategoryActivity : AppCompatActivity(), OnCharityEventClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //TODO: УБРАТЬ!!!
        val events = EventTabInteractor.requestEvents("")
        recyclerView.adapter = EventListAdapter(events,this)
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

    override fun onCharityEventClick(event: CharityEvent) {
        val intent = Intent(this,EventActivity::class.java)
        intent.putExtra(EventActivity.EVENT_TITLE,event.title)
        intent.putExtra(EventActivity.EVENT_DESCRIPTION,event.description)
        intent.putExtra(EventActivity.EVENT_PICTURES_ARRAY,event.picturesUrlArray)
        intent.putExtra(EventActivity.EVENT_START_DATE,event.startDate.format(DateTimeFormatter.BASIC_ISO_DATE))
        intent.putExtra(EventActivity.EVENT_END_DATE,event.endDate.format(DateTimeFormatter.BASIC_ISO_DATE))
        intent.putExtra(EventActivity.EVENT_DONATORS_PICTURES_ARRAY,event.donatorsPicturesUrlArray)
        startActivity(intent)
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
