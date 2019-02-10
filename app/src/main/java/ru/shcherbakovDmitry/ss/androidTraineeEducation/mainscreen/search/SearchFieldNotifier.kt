package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search

object SearchFieldNotifier {

    var activeItem = 0
    private val listOfObservers: ArrayList<SearchFieldObserver> = ArrayList()

    fun attachObserver(observer: SearchFieldObserver) {
        listOfObservers.add(observer)
    }

    fun detachObserver(observer: SearchFieldObserver) {
        listOfObservers.remove(observer)
    }

    fun findContent(key: String?) {
        listOfObservers[activeItem].requestContent(key)
    }
}