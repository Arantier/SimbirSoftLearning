package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_events_tab.view.*
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.eventscreen.EventActivity
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.R

class EventTabFragment : MvpAppCompatFragment(), EventTabMvpView, OnCharityEventClickListener {

    @InjectPresenter
    lateinit var presenter: EventMvpPresenter
    private lateinit var fragmentView: View

    override fun setContent(eventArray: Array<CharityEvent>) {
        if (eventArray.isEmpty()) {
            fragmentView.layoutEventTabBackground.visibility = View.VISIBLE
        } else {
            fragmentView.layoutEventTabBackground.visibility = View.GONE
            fragmentView.recyclerviewEventTab.adapter = EventListAdapter(eventArray,this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_events_tab, container, false)
        fragmentView.recyclerviewEventTab.layoutManager = LinearLayoutManager(context)
        return fragmentView
    }

    override fun onCharityEventClick(event: CharityEvent) {
        val intent = Intent(context, EventActivity::class.java)
        intent.putExtra(EventActivity.EVENT_TITLE,event.title)
        intent.putExtra(EventActivity.EVENT_DESCRIPTION,event.description)
        intent.putExtra(EventActivity.EVENT_PICTURES_ARRAY,event.picturesUrlArray)
        intent.putExtra(EventActivity.EVENT_START_DATE,event.startDate.format(DateTimeFormatter.BASIC_ISO_DATE))
        intent.putExtra(EventActivity.EVENT_END_DATE,event.endDate.format(DateTimeFormatter.BASIC_ISO_DATE))
        intent.putExtra(EventActivity.EVENT_DONATORS_PICTURES_ARRAY,event.donatorsPicturesUrlArray)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): EventTabFragment {
            val fragment = EventTabFragment()
            return fragment
        }
    }
}