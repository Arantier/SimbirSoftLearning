package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_events_tab.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data_classes.Charity
import ru.shcherbakovdv.ss.trainee.EventActivity
import ru.shcherbakovdv.ss.trainee.data_classes.representations.OnCharityClickListener
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import ru.shcherbakovdv.ss.trainee.utilites.extensions.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible

class CharitiesTabFragment : MvpAppCompatFragment(), CharityTabMvpView, OnCharityClickListener {

    @InjectPresenter
    lateinit var presenter: CharitiesTabMvpPresenter
    private lateinit var fragmentView: View

    override fun setContent(charityArray: Array<Charity>) {
        if (charityArray.isEmpty()) {
            fragmentView.layoutEventTabBackground.makeVisible()
        } else {
            fragmentView.layoutEventTabBackground.makeGone()
            fragmentView.recyclerviewEventTab.adapter = CharitiesListAdapter(charityArray, this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View {
        fragmentView = inflater.inflate(R.layout.fragment_events_tab, container, false)
        return fragmentView
    }

    override fun onCharityEventClick(event: Charity) {
        val eventJson = JsonUtilities.gson.toJson(event)
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