package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_search_screen.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab.EventTabFragment
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.organisationtab.OrganisationTabFragment

class SearchFragment : MvpAppCompatFragment() {

    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_screen, container, false)
        viewPager = view.viewPager_search.apply {
            adapter = TabPagerAdapter(childFragmentManager, context)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {

                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                }

                override fun onPageSelected(position: Int) {
                    SearchFieldNotifier.activeItem = position
                    SearchFieldNotifier.findContent("")
                }

            })
        }
        return view
    }

    companion object {
        val TAG = "search_fragment"

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private class TabPagerAdapter constructor(fm: FragmentManager, val context:Context?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    EventTabFragment.newInstance()
                }
                1 -> {
                    OrganisationTabFragment.newInstance()
                }
                else -> {
                    OrganisationTabFragment.newInstance()
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> {
                    context?.getString(R.string.search_tab_events) ?: "ERROR"
                }
                1 -> {
                    context?.getString(R.string.search_tab_organisations) ?: "ERROR"
                }
                else -> {
                    "Error"
                }
            }
        }

        override fun getCount(): Int = 2

    }
}
