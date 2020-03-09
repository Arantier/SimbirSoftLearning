package ru.shcherbakovdv.ss.trainee.ui.search


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_search_screen.view.*
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.utilites.Logger


class SearchFragment : MvpAppCompatFragment() {

    private lateinit var viewPager: ViewPager
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search_screen, container, false).apply {
                viewPager = viewpagerSearchScreen.apply {
                    adapter = SearchTabPagerAdapter(childFragmentManager, context)
                    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrollStateChanged(p0: Int) = Unit

                        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) = Unit

                        override fun onPageSelected(position: Int) {
                            SearchFieldNotifier.activeItem = position
                            Logger.flatDebug("Page was swapped on position $position")
                        }

                    })
                    currentItem = savedInstanceState?.getInt("position") ?: 0
                }
            }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", viewPager.currentItem)
    }

    companion object {
        val TAG = SearchFragment::class.simpleName

        fun newInstance() = SearchFragment()
    }
}
