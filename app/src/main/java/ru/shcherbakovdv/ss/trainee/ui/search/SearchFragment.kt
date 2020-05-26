package ru.shcherbakovdv.ss.trainee.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_search_screen.*
import kotlinx.android.synthetic.main.fragment_search_screen.view.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.shcherbakovdv.ss.trainee.R


class SearchFragment : MvpAppCompatFragment(), SearchMvpView {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    override fun setPage(position: Int) {
        viewpagerSearchScreen.currentItem = position
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search_screen, container, false).apply {
                viewpagerSearchScreen.apply {
                    adapter = SearchTabPagerAdapter(childFragmentManager, context)
                    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrollStateChanged(p0: Int) = Unit

                        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) = Unit

                        override fun onPageSelected(position: Int) {
                            presenter.position = position
                        }

                    })
                }
            }

    companion object {
        val TAG = SearchFragment::class.simpleName

        fun newInstance() = SearchFragment()
    }
}
