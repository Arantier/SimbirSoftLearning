package ru.shcherbakovdv.ss.trainee.ui.search

import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter

class SearchPresenter : ReactiveMvpPresenter<SearchMvpView>() {

    var position = 0
        set(value) {
            field = value
            if (value == 1) {
                SearchFieldNotifier.searchField.apply {
                    onNext(this.value ?: "")
                }
            }
            viewState.setPage(position)
        }
}