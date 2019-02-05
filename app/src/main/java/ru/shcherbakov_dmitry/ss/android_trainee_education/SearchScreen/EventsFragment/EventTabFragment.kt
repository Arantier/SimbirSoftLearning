package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.EventsFragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_events_tab.view.*
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.CharityEvent

import ru.shcherbakov_dmitry.ss.android_trainee_education.R
import java.util.*

class EventTabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_events_tab, container, false)
        return view
    }

    companion object {
        fun newInstance(): EventTabFragment {
            val fragment = EventTabFragment()
            return fragment
        }
    }
}
