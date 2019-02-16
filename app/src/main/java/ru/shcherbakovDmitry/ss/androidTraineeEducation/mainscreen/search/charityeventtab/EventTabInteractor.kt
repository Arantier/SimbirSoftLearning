package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.charityeventtab

import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.dataclasses.CharityEvent
import java.util.*

object EventTabInteractor {

    fun requestEvents(): Array<CharityEvent> {
        val eventArray = ArrayList<CharityEvent>()
        val random = Random()
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            eventArray.add(CharityEvent(string,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/89D9586D-AA7B-48C8-BDE4-251613E56E55.png",
                    "01.01.2019",
                    "31.12.2019"))
        }
        return eventArray.toTypedArray()
    }

}