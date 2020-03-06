package ru.shcherbakovdv.ss.trainee.ui.search.charities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_charity_tab.*
import kotlinx.android.synthetic.main.fragment_charity_tab.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.EventActivity
import ru.shcherbakovdv.ss.trainee.data.OnCharityClickListener
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils
import ru.shcherbakovdv.ss.trainee.utilites.extensions.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class CharitiesTabFragment : MvpAppCompatFragment(), CharityTabMvpView, OnCharityClickListener {

    @InjectPresenter
    lateinit var presenter: CharitiesTabMvpPresenter

    override fun setContent(charityArray: Array<Charity>) {
        if (charityArray.isEmpty()) {
            layoutCharityTabBackground.makeVisible()
        } else {
            layoutCharityTabBackground.makeGone()
        }
        recyclerviewEventTab.adapter = CharitiesListAdapter(charityArray, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_charity_tab, container, false)

    override fun onCharityEventClick(event: Charity) {
        val eventJson = JsonUtils.gson.toJson(event)
        val intent = context!!.getClassIntent<EventActivity>()
        intent.putExtra(EventActivity.EVENT_JSON, eventJson)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): CharitiesTabFragment {
            return CharitiesTabFragment()
        }
    }
}