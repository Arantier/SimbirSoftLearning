package ru.shcherbakov_dmitry.ss.android_trainee_education.HelpScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.shcherbakov_dmitry.ss.android_trainee_education.R

//TODO: Разберись
class HelpScreen : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance():HelpScreen{
            return HelpScreen()
        }
    }
}
