package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.shcherbakovdv.ss.trainee.R

class HelpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help_screen, container, false)
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
