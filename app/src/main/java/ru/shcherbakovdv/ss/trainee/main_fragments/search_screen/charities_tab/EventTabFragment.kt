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
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import ru.shcherbakovdv.ss.trainee.utilites.getClassIntent
import ru.shcherbakovdv.ss.trainee.utilites.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.makeVisible

class EventTabFragment : MvpAppCompatFragment(), EventTabMvpView, OnCharityEventClickListener {

    @InjectPresenter
    lateinit var presenter: EventMvpPresenter
    private lateinit var fragmentView: View

    override fun setContent(charityArray: Array<Charity>) {
        if (charityArray.isEmpty()) {
            fragmentView.layoutEventTabBackground.makeVisible()
        } else {
            fragmentView.layoutEventTabBackground.makeGone()
            fragmentView.recyclerviewEventTab.adapter = EventListAdapter(charityArray, this)
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
        fun newInstance(): EventTabFragment {
            return EventTabFragment()
        }
    }
}