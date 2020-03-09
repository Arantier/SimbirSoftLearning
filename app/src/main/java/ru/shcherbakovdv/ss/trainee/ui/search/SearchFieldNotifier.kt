package ru.shcherbakovdv.ss.trainee.ui.search

import io.reactivex.subjects.BehaviorSubject

object SearchFieldNotifier {
    val searchField: BehaviorSubject<String> = BehaviorSubject.create()
}