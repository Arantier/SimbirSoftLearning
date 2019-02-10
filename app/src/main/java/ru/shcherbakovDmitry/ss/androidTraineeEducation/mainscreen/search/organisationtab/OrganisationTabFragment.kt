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

class OrganisationTabFragment : MvpAppCompatFragment(), OrganisationTabMvpView, OnOrganisationClickListener {

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
            text_search_info.text = String.format(searchResultPattern, searchResultsInfo)
            recycler_organisations_list.adapter = OrganisationListAdapter(organisationArray, this@OrganisationTabFragment)
        }
    }

    override fun startOrganisationActivity(organisationArray : Array<Organisation>) {
        Toast.makeText(context, "Requested activity", Toast.LENGTH_SHORT).show()
    }

    override fun onOrganisationClick(organisationId: Int) {
        presenter.requestOrganisationActivity(organisationId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_organisation_tab, container, false)
        fragmentView.recycler_organisations_list.apply {
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

    class OrganisationListAdapter constructor(val organisationArray: Array<Organisation>, val listener: OnOrganisationClickListener) : RecyclerView.Adapter<OrganisationListAdapter.OrganisationViewHolder>() {

        class OrganisationViewHolder constructor(val view: TextView) : RecyclerView.ViewHolder(view) {

            fun bind(organisation: Organisation, onOrganisationClickListener: OnOrganisationClickListener) {
                view.text = organisation.name
                view.setOnClickListener {
                    onOrganisationClickListener.onOrganisationClick(organisation.organisationId)
                }
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
            viewHolder.bind(organisationArray[position], listener)
        }
    }
}

interface OnOrganisationClickListener {
    fun onOrganisationClick(organisationId: Int)
}