package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_search_screen.view.*
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R

class SearchFragment : MvpAppCompatFragment() {

    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_screen, container, false)
        viewPager = view.viewpagerSearchScreen.apply {
            adapter = SearchTabPagerAdapter(childFragmentManager, context)
            addOnPageChangeListener(object : BaseOnTabChangeListener() {
                override fun onPageSelected(position: Int) {
                    SearchFieldNotifier.activeItem = position
                    SearchFieldNotifier.findContent()
                }

            })
        }
        return view
    }

    companion object {
        val TAG = SearchFragment::class.simpleName

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
