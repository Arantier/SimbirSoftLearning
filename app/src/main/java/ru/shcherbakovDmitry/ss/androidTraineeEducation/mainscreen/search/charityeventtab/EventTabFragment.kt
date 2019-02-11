package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab


import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_events_tab.view.*
import kotlinx.android.synthetic.main.item_event_list.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.CharityEvent
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import java.util.*

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

    class EventListAdapter(private val eventArray: Array<CharityEvent>) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            private fun getLocaleQuantityString(id: Int, quantity: Int): String {
                val configuration = Configuration(view.context!!.resources.configuration).apply {
                    setLocale(Locale("ru"))
                }
                return view.context!!.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
            }

            fun bind(event: CharityEvent) {
                view.apply {
                    titleEventItem.text = event.title
                    textEventItemDescription.text = event.description
                    val startDateString = event.startDate.split(".")
                    val startDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, startDateString[2].toInt())
                        set(Calendar.MONTH, startDateString[1].toInt())
                        set(Calendar.DAY_OF_MONTH, startDateString[0].toInt())
                    }
                    val endDateString = event.endDate.split(".")
                    val endDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, endDateString[2].toInt())
                        set(Calendar.MONTH, endDateString[1].toInt())
                        set(Calendar.DAY_OF_MONTH, endDateString[0].toInt())
                    }
                    var dayDifference = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)
                    if (endDate.get(Calendar.DAY_OF_YEAR) < startDate.get(Calendar.DAY_OF_YEAR)) {
                        dayDifference += if (startDate.get(Calendar.YEAR) % 4 == 0) {
                            366
                        } else {
                            365
                        }
                    }
                    textEventItemDate.text = String.format(resources.getString(R.string.event_date_expiration_info),
                            getLocaleQuantityString(R.plurals.event_date_expiration, dayDifference),
                            startDateString[0].toInt(),
                            startDateString[1].toInt(),
                            endDateString[0].toInt(),
                            endDateString[1].toInt()
                    )
                    Glide.with(view)
                            .load(event.pictureURL)
                            .into(imageEventItem)
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, state: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_event_list, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = eventArray.size

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Int) = viewHolder.bind(eventArray[item])

    }
}