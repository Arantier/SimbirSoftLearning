package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search

object SearchFieldNotifier {

    var inactiveItem = 1
    var activeItem = 0
        set(value){
            inactiveItem = field
            field = value
        }
    private val listOfObservers: ArrayList<SearchFieldObserver> = ArrayList()

    fun attachObserver(observer: SearchFieldObserver) {
        listOfObservers.add(observer)
    }

    fun detachObserver(observer: SearchFieldObserver) {
        listOfObservers.remove(observer)
    }

    fun findContent(key: String?) {
        listOfObservers[inactiveItem].requestContent(key)
    }
}