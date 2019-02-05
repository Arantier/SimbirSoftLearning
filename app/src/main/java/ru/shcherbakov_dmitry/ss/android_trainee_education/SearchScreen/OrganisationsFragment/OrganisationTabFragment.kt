package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.OrganisationsFragment


import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_organisation_tab.view.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.CharityEvent
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.Organisation

import ru.shcherbakov_dmitry.ss.android_trainee_education.R

class OrganisationTabFragment : MvpAppCompatFragment(), OrganisationTabMvpView, OnOrganisationClickListener {

    @InjectPresenter lateinit var presenter: OrganisationTabPresenter
    lateinit var fragmentView: View

    override fun setContent(organisationArray: Array<Organisation>) {
        val searchResultInfo = getString(R.string.search_result_info)
        val searchResults = resources.getQuantityString(R.plurals.search_result_number, organisationArray.size, organisationArray.size)
        fragmentView.text_search_info.text = String.format(searchResultInfo, searchResults)
        fragmentView.recycler_organisations_list.adapter = OrganisationListAdapter(organisationArray, this)
    }

    override fun startItemActivity(eventArray: Array<CharityEvent>) {
        //TODO: Сделать потом
    }

    override fun onOrganisationClick(organisationId: Int) {
        presenter.requestOrganisationActivity(organisationId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.isActive = true
        fragmentView = inflater.inflate(R.layout.fragment_organisation_tab, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.requestContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.isActive = false
    }

    companion object {
        fun newInstance(): OrganisationTabFragment {
            return OrganisationTabFragment()
        }
    }

    class OrganisationListAdapter constructor(val organisationArray: Array<Organisation>, val listener: OnOrganisationClickListener) : RecyclerView.Adapter<OrganisationListAdapter.OrganisationViewHolder>() {

        class OrganisationViewHolder constructor(val view: TextView) : RecyclerView.ViewHolder(view) {

            fun bind(organisation: Organisation, onOrganisationClickListener: OnOrganisationClickListener) {
                (view as TextView).text = organisation.name
                view.setOnClickListener {
                    onOrganisationClickListener.onOrganisationClick(organisation.organisationId)
                }
            }

        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewMode: Int): OrganisationViewHolder {
            val view = TextView(viewGroup.context, null, R.style.OrganisationListItemStyle)
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            return OrganisationViewHolder(view)
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