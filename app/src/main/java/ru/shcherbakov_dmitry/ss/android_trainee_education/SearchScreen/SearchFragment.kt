package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen


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
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_search_screen.view.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.R
import ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.EventsFragment.EventTabFragment
import ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.OrganisationsFragment.OrganisationTabFragment

class SearchFragment : MvpAppCompatFragment(), SearchMvpView {

    private lateinit var organisationTab: OrganisationTabFragment
    private lateinit var eventTab: EventTabFragment

    private lateinit var viewPager: ViewPager

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    override fun selectTab(id: Int) {
        viewPager.currentItem = id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_screen, container, false)
        viewPager = view.viewPager_search
        eventTab = EventTabFragment.newInstance()
        organisationTab = OrganisationTabFragment.newInstance()
        viewPager.adapter = TabPagerAdapter(fragmentManager!!,context, eventTab, organisationTab)
        viewPager.currentItem = presenter.currentTab
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageSelected(position: Int) {
                presenter.currentTab = position
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

        })
        return view
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private class TabPagerAdapter constructor(fm: FragmentManager,val context:Context?, val eventTab: EventTabFragment, val organisationTab: OrganisationTabFragment) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    eventTab
                }
                else -> {
                    organisationTab
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> {
                    context?.getString(R.string.search_tab_events) ?: "ERROR"
                }
                else -> {
                    context?.getString(R.string.search_tab_organisations) ?: "ERROR"
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

    }
}
