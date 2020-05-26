package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_category.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.OnCharityClickListener
import ru.shcherbakovdv.ss.trainee.ui.search.charities.CharitiesListAdapter
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

class CategoryActivity : MvpAppCompatActivity(R.layout.activity_category), OnCharityClickListener, CategoryMvpView {

    @InjectPresenter
    lateinit var presenter: CategoryPresenter

    override fun setLoadingState() {
        recyclerView.makeGone()
        imageError.makeGone()
        textError.makeGone()
        progressBar.makeVisible()
    }

    override fun setErrorState() {
        recyclerView.makeGone()
        progressBar.makeGone()
        imageError.makeVisible()
        textError.makeVisible()
    }

    override fun updateList(events: Array<Charity>) {
        if (recyclerView.visibility == View.GONE) {
            recyclerView.makeVisible()
            progressBar.makeGone()
            imageError.makeGone()
            textError.makeGone()
        }
        recyclerView.adapter = CharitiesListAdapter(events, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        presenter.categoryId = intent.getIntExtra(CATEGORY_ID, -1)

        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.btn_back)
            setNavigationOnClickListener {
                finish()
            }
            inflateMenu(R.menu.category_toolbar)
        }
        toolbarTitle.text = intent.getStringExtra(CATEGORY_NAME)
    }

    override fun onStart() {
        super.onStart()
        presenter.observeNetwork(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.disposeNetwork(this)
    }

    override fun onCharityEventClick(event: Charity) {
        val intent = Intent(this, EventActivity::class.java)
        val eventJson = JsonUtils.gson.toJson(event)
        intent.putExtra(EventActivity.EVENT_JSON, eventJson)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_NAME = "category_name"
    }

}
