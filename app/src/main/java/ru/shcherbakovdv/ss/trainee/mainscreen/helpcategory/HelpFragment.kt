package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import ru.shcherbakovdv.ss.trainee.HelpCategoryActivity
import ru.shcherbakovdv.ss.trainee.R

class HelpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        view.itemHelpScreenKids.setOnClickListener {
            startCategoryActivity(HelpCategoryActivity.CATEGORY_KIDS)
        }
        view.itemHelpScreenAdults.setOnClickListener {
            startCategoryActivity(HelpCategoryActivity.CATEGORY_ADULTS)
        }
        view.itemHelpScreenElders.setOnClickListener {
            startCategoryActivity(HelpCategoryActivity.CATEGORY_ELDERS)
        }
        view.itemHelpScreenAnimals.setOnClickListener {
            startCategoryActivity(HelpCategoryActivity.CATEGORY_ANIMALS)
        }
        view.itemHelpScreenEvents.setOnClickListener {
            startCategoryActivity(HelpCategoryActivity.CATEGORY_EVENTS)
        }
        return view
    }

    fun startCategoryActivity(id: Int) {
        val intent = Intent(context,HelpCategoryActivity::class.java).apply {
            putExtra(HelpCategoryActivity.CATEGORY_ID,id)
        }
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        val TAG = HelpFragment::class.simpleName

        @JvmStatic
        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }
}
