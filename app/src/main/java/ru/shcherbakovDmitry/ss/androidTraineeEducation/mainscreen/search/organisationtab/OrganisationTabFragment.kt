package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab


import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_organisation_tab.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.Organisation
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import java.util.*

class OrganisationTabFragment : MvpAppCompatFragment(), OrganisationTabMvpView {

    @InjectPresenter
    lateinit var presenter: OrganisationTabPresenter
    private lateinit var fragmentView: View

    private fun getLocaleQuantityString(id: Int, quantity: Int): String {
        val configuration = Configuration(context!!.resources.configuration).apply {
            setLocale(Locale("ru"))
        }
        return context!!.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
    }

    override fun setContent(organisationArray: Array<Organisation>) {
        val searchResultPattern = getString(R.string.search_result_info)
        val searchResultsInfo = getLocaleQuantityString(R.plurals.organisation_search_quantity, organisationArray.size)
        fragmentView.apply {
            textOrganisationTabSearchInfo.text = String.format(searchResultPattern, searchResultsInfo)
            recyclerviewOrganisationList.adapter = OrganisationListAdapter(organisationArray)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_organisation_tab, container, false)
        fragmentView.recyclerviewOrganisationList.apply {
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
        presenter.requestContent()
        return fragmentView
    }

    companion object {
        fun newInstance(): OrganisationTabFragment {
            return OrganisationTabFragment()
        }
    }

    class OrganisationListAdapter(private val organisationArray: Array<Organisation>) : RecyclerView.Adapter<OrganisationListAdapter.OrganisationViewHolder>() {

        class OrganisationViewHolder(val view: TextView) : RecyclerView.ViewHolder(view) {

            fun bind(organisation: Organisation) {
                view.text = organisation.name
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewMode: Int): OrganisationViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_organisation_list, parent, false)
            return OrganisationViewHolder(view as TextView)
        }

        override fun getItemCount(): Int {
            return organisationArray.size
        }

        override fun onBindViewHolder(viewHolder: OrganisationViewHolder, position: Int) {
            viewHolder.bind(organisationArray[position])
        }
    }
}