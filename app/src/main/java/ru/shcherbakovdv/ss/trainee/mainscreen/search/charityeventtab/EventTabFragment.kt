package ru.shcherbakovdv.ss.trainee.mainscreen.search.charityeventtab


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_events_tab.view.*
import ru.shcherbakovdv.ss.trainee.mainscreen.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.R

class EventTabFragment : MvpAppCompatFragment(), EventTabMvpView {

    @InjectPresenter
    lateinit var presenter: EventMvpPresenter
    private lateinit var fragmentView: View

    override fun setContent(eventArray: Array<CharityEvent>) {
        if (eventArray.isEmpty()){
            fragmentView.layoutEventTabBackground.visibility = View.VISIBLE
        } else {
            fragmentView.layoutEventTabBackground.visibility = View.GONE
            fragmentView.recyclerviewEventTab.adapter = EventListAdapter(eventArray)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_events_tab, container, false)
        fragmentView.recyclerviewEventTab.layoutManager = LinearLayoutManager(context)
        return fragmentView
    }

    companion object {
        fun newInstance(): EventTabFragment {
            val fragment = EventTabFragment()
            return fragment
        }
    }
}