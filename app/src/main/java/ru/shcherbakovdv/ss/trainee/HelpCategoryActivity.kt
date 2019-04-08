package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_category.*
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.eventscreen.EventActivity
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventListAdapter
import ru.shcherbakovdv.ss.trainee.dataclasses.EventProvider
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.OnCharityEventClickListener
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import java.io.File
import java.io.FileReader

class HelpCategoryActivity : AppCompatActivity(), OnCharityEventClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //TODO: Отладочный код
        val events = EventProvider.requestEventsOld("")

        recyclerView.adapter = EventListAdapter(events,this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.btn_back)
            setNavigationOnClickListener {
                finish()
            }
            inflateMenu(R.menu.category_toolbar)
        }
    }

    override fun onCharityEventClick(event: CharityEvent) {
        val intent = Intent(this, EventActivity::class.java)
        val builder = GsonBuilder()
        val gson = builder.create()
        intent.apply {
            putExtra(EventActivity.EVENT_TITLE,event.title)
            putExtra(EventActivity.EVENT_DESCRIPTION,event.description)
            putExtra(EventActivity.EVENT_PICTURES_ARRAY,event.picturesUrlArray)
            putExtra(EventActivity.EVENT_START_DATE,event.startDate.format(DateTimeFormatter.ISO_DATE))
            putExtra(EventActivity.EVENT_END_DATE,event.endDate.format(DateTimeFormatter.ISO_DATE))
            putExtra(EventActivity.EVENT_ORGANISATION, gson.toJson(event.organisation))
            putExtra(EventActivity.EVENT_DONATORS_PICTURES_ARRAY,event.donatorsPicturesUrlArray)
        }
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_ID = "category_id"
    }

}
