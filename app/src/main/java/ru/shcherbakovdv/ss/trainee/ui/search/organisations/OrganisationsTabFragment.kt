package ru.shcherbakovdv.ss.trainee.ui.search.organisations


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_organisation_tab.*
import kotlinx.android.synthetic.main.fragment_organisation_tab.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.data.Organisation
import java.util.*

class OrganisationsTabFragment : MvpAppCompatFragment(R.layout.fragment_organisation_tab), OrganisationsTabMvpView {

    val presenter by moxyPresenter { OrganisationsTabPresenter() }

    private fun getLocaleQuantityString(id: Int, quantity: Int): String {
        return context?.let {context->
            val configuration = Configuration(context.resources.configuration).apply {
                setLocale(Locale("ru"))
            }
            context.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
        } ?: ""
    }

    override fun setContent(organisations: Array<Organisation>) {
        val searchResultPattern = getString(R.string.search_result_info)
        val searchResultsInfo = getLocaleQuantityString(R.plurals.organisation_search_quantity, organisations.size)
        textOrganisationTabSearchInfo.text = String.format(searchResultPattern, searchResultsInfo)
        recyclerviewOrganisationList.adapter = OrganisationsListAdapter(organisations)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_organisation_tab, container, false)
            .apply {
                recyclerviewOrganisationList.apply {
                    val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                    addItemDecoration(divider)
                }
            }

    companion object {
        fun newInstance() = OrganisationsTabFragment()
    }
}