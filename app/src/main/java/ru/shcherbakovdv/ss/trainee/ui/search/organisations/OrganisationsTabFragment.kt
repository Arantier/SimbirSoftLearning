package ru.shcherbakovdv.ss.trainee.ui.search.organisations


import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_organisation_tab.*
import kotlinx.android.synthetic.main.fragment_organisation_tab.view.*
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier
import java.util.*

class OrganisationsTabFragment : MvpAppCompatFragment(), OrganisationsTabMvpView {

    @InjectPresenter
    lateinit var presenter: OrganisationsTabPresenter

    private fun getLocaleQuantityString(id: Int, quantity: Int): String {
        return context?.let {context->
            val configuration = Configuration(context.resources.configuration).apply {
                setLocale(Locale("ru"))
            }
            context.createConfigurationContext(configuration).resources.getQuantityString(id, quantity, quantity)
        } ?: ""
    }

    override fun setContent(organisationArray: Array<Organisation>) {
        val searchResultPattern = getString(R.string.search_result_info)
        val searchResultsInfo = getLocaleQuantityString(R.plurals.organisation_search_quantity, organisationArray.size)
        textOrganisationTabSearchInfo.text = String.format(searchResultPattern, searchResultsInfo)
        recyclerviewOrganisationList.adapter = OrganisationsListAdapter(organisationArray)
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