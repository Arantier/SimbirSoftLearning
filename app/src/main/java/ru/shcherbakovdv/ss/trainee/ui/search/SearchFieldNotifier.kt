package ru.shcherbakovdv.ss.trainee.ui.search

import io.reactivex.subjects.BehaviorSubject

object SearchFieldNotifier {

    val searchField: BehaviorSubject<String> = BehaviorSubject.create()

    private var inactiveItem = 1
    var activeItem = 0
        set(value){
            inactiveItem = field
            field = value

            if (value == 1) {
                searchField.onNext(searchField.value ?: "")
            }
        }
}