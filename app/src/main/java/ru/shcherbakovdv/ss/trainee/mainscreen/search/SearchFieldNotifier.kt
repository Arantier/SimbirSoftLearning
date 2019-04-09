package ru.shcherbakovdv.ss.trainee.mainscreen.search

import ru.shcherbakovdv.ss.trainee.mainscreen.search.organisationtab.OrganisationTabPresenter

object SearchFieldNotifier {

    private var inactiveItem = 1
    var activeItem = 0
        set(value){
            inactiveItem = field
            field = value

            //Кусок кода для 2 задания, уберу когда надо будет
            if (listOfObservers[inactiveItem]::class.java == OrganisationTabPresenter::class.java){
                listOfObservers[inactiveItem].requestContent("")
            }
        }
    private val listOfObservers: ArrayList<SearchFieldObserver> = ArrayList()

    fun attachObserver(observer: SearchFieldObserver) {
        listOfObservers.add(observer)
    }

    fun detachObserver(observer: SearchFieldObserver) {
        listOfObservers.remove(observer)
    }

    fun findContent(key: String?) {
        if (listOfObservers.isNotEmpty()) {
            listOfObservers[activeItem].requestContent(key)
        }
    }
}