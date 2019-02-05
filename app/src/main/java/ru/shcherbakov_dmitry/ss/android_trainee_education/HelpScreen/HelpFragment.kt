package ru.shcherbakov_dmitry.ss.android_trainee_education.HelpScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.shcherbakov_dmitry.ss.android_trainee_education.R

//TODO: Разберись
class HelpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help_screen, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance():HelpFragment{
            return HelpFragment()
        }
    }
}
