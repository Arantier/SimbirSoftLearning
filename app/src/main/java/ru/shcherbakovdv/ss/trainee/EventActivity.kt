package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_event.*
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent

class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
    }

    companion object {
        const val EVENT_TITLE = "title"
        const val EVENT_DESCRIPTION = "description"
        const val EVENT_PICTURES_ARRAY = "pic_array"
        const val EVENT_START_DATE = "start_date"
        const val EVENT_END_DATE = "end_date"
        const val EVENT_DONATORS_PICTURES_ARRAY = "donators_pic_array"
    }

}
