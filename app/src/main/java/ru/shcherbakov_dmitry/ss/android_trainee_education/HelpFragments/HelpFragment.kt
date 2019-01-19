package ru.shcherbakov_dmitry.ss.android_trainee_education.HelpFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.shcherbakov_dmitry.ss.android_trainee_education.R

//TODO: Разберись
class HelpFragment : Fragment() {

    interface HelpInterface{
        fun onHelpShow()

        fun onHelpClose()
    }

    private lateinit var activityCallback:HelpInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        activityCallback.onHelpShow()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activityCallback.onHelpClose()
    }

    companion object {
        @JvmStatic
        fun newInstance(helpInterface: HelpInterface):HelpFragment{
            val fragment = HelpFragment()
            fragment.activityCallback = helpInterface
            return fragment
        }
    }
}
