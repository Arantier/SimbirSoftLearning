package ru.shcherbakovdv.ss.trainee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_category.view.*
import ru.shcherbakovdv.ss.trainee.R

class ConnectionLostFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_connection_lost, container, false)
        view.textError.text = arguments?.getString(MESSAGE_TAG) ?: "Непредвиденная ошибка"
        return view
    }

    companion object {
        val TAG = ConnectionLostFragment::class.java.simpleName
        const val MESSAGE_TAG = "message"

        fun newInstance(message: String): ConnectionLostFragment {
            val fragment = ConnectionLostFragment()
            val args = Bundle().apply { putString(MESSAGE_TAG, message) }
            fragment.arguments = args
            return fragment
        }
    }
}