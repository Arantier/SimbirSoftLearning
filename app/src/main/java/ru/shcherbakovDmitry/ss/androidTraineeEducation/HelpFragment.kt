package ru.shcherbakovDmitry.ss.androidTraineeEducation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HelpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help_screen, container, false)
    }

    companion object {
        @JvmStatic
        val TAG = "help_fragment"

        @JvmStatic
        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }
}
