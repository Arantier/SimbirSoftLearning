package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_category.*
import ru.shcherbakovdv.ss.trainee.data_classes.Charity
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab.CharitiesListAdapter
import ru.shcherbakovdv.ss.trainee.data_classes.representations.OnCharityClickListener
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class CategoryActivity : MvpAppCompatActivity(), OnCharityClickListener, CategoryMvpView {

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
        val eventJson = JsonUtilities.gson.toJson(event)
        intent.putExtra(EventActivity.EVENT_JSON, eventJson)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_NAME = "category_name"
    }

}
