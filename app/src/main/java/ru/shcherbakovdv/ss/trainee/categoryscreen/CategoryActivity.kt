package ru.shcherbakovdv.ss.trainee.categoryscreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_category.*
import org.threeten.bp.LocalDate
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.eventscreen.EventActivity
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.EventListAdapter
import ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab.OnCharityEventClickListener
import ru.shcherbakovdv.ss.trainee.utilites.LocalDateJsonSerializer

class CategoryActivity : MvpAppCompatActivity(), OnCharityEventClickListener, CategoryMvpView {

    @InjectPresenter
    lateinit var presenter : CategoryPresenter

    override fun setLoadingState() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        imageError.visibility = View.GONE
        textError.visibility = View.GONE
    }

    override fun updateList(events: Array<CharityEvent>) {if (recyclerView.visibility == View.GONE) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        imageError.visibility = View.GONE
        textError.visibility = View.GONE
    }
        recyclerView.adapter = EventListAdapter(events,this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun setErrorState() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        imageError.visibility = View.VISIBLE
        textError.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        presenter.requestEvents(intent.getIntExtra(CATEGORY_ID,-1))

        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.btn_back)
            setNavigationOnClickListener {
                finish()
            }
            inflateMenu(R.menu.category_toolbar)
        }

        toolbarTitle.text = intent.getStringExtra(CATEGORY_NAME)
    }

    override fun onCharityEventClick(event: CharityEvent) {
        val intent = Intent(this, EventActivity::class.java)
        val gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateJsonSerializer())
                .create()
        val eventJson = gson.toJson(event)
        intent.putExtra(EventActivity.EVENT_JSON,eventJson)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_NAME = "category_name"
    }

}
