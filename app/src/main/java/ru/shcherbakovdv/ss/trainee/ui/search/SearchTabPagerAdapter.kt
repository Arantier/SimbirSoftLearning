package ru.shcherbakovdv.ss.trainee.ui.search

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.ui.search.charities.CharitiesTabFragment
import ru.shcherbakovdv.ss.trainee.ui.search.organisations.OrganisationsTabFragment

class SearchTabPagerAdapter(fm: FragmentManager, val context: Context?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            EVENT_TAB_POSITION -> CharitiesTabFragment.newInstance()
            ORGANISATION_TAB_POSITION -> OrganisationsTabFragment.newInstance()
            else -> OrganisationsTabFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            EVENT_TAB_POSITION -> context?.getString(R.string.search_tab_events) ?: "ERROR"
            ORGANISATION_TAB_POSITION -> context?.getString(R.string.search_tab_organisations) ?: "ERROR"
            else -> "Error"
        }
    }

    override fun getCount(): Int = 2

    companion object {
        private const val EVENT_TAB_POSITION = 0
        private const val ORGANISATION_TAB_POSITION = 1
    }
}