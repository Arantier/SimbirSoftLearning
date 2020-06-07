package ru.shcherbakovdv.ss.trainee.ui.search.charities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_charity_tab.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.CharityPageActivity
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.OnCharityClickListener
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import ru.shcherbakovdv.ss.trainee.utilites.extensions.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

class CharitiesTabFragment : MvpAppCompatFragment(R.layout.fragment_charity_tab), CharityTabMvpView, OnCharityClickListener {

    private val presenter by moxyPresenter { CharitiesTabMvpPresenter() }

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
        context?.let { context ->
            val intent = context.getClassIntent<CharityPageActivity>()
            intent.putExtra(CharityPageActivity.EVENT_JSON, eventJson)
            startActivity(intent)
        }
        if (context==null) {
            Logger.flatError("Context is null in CharitiesTabFragment")
        }
    }

    companion object {
        fun newInstance(): CharitiesTabFragment {
            return CharitiesTabFragment()
        }
    }
}