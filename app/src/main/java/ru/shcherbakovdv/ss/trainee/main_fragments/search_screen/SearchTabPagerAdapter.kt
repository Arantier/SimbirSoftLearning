package ru.shcherbakovdv.ss.trainee.main_fragments.search_screen

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.charities_tab.EventTabFragment
import ru.shcherbakovdv.ss.trainee.main_fragments.search_screen.organisations_tab.OrganisationTabFragment

class SearchTabPagerAdapter(fm: FragmentManager, val context: Context?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            EVENT_TAB_POSITION -> EventTabFragment.newInstance()
            ORGANISATION_TAB_POSITION -> OrganisationTabFragment.newInstance()
            else -> OrganisationTabFragment.newInstance()
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