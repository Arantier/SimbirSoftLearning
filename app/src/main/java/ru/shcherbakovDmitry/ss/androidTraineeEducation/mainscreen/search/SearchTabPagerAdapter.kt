package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab.EventTabFragment
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab.OrganisationTabFragment

class SearchTabPagerAdapter(fm: FragmentManager, val context: Context?) : FragmentPagerAdapter(fm) {

    private val EVENT_TAB_POSITION = 0
    private val ORGANISATION_TAB_POSITION = 1

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
}