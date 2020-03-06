package ru.shcherbakovdv.ss.trainee.ui.search

import ru.shcherbakovdv.ss.trainee.ui.search.organisations.OrganisationsTabPresenter

object SearchFieldNotifier {

    private var inactiveItem = 1
    var activeItem = 0
        set(value){
            inactiveItem = field
            field = value

            if (observers[inactiveItem]::class.java == OrganisationsTabPresenter::class.java){
                observers[inactiveItem].requestContent("")
            }
        }
    private val observers: ArrayList<SearchFieldObserver> = ArrayList()

    fun attachObserver(observer: SearchFieldObserver) {
        observers.add(observer)
    }

    fun detachObserver(observer: SearchFieldObserver) {
        observers.remove(observer)
    }

    fun findContent(key: String?) {
        if (observers.isNotEmpty()) {
            observers[activeItem].requestContent(key)
        }
    }
}