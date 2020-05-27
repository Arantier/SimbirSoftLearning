package ru.shcherbakovdv.ss.trainee.ui.search.charities

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.ReactiveMvpPresenter
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.ui.search.SearchFieldNotifier

class CharitiesTabMvpPresenter : ReactiveMvpPresenter<CharityTabMvpView>() {

    init {
        /*
        SearchFieldNotifier.searchField
                .flatMap { key -> CharitiesProvider.requestCharities(key)
                        .subscribeOn(Schedulers.io())}
                .doOnEach { Logger.flatDebug("Принимаю очередной успешно обработанный элемент!") }
                .observeOn(Schedulers.io())
                .toList()
                .doOnEvent { t1, t2 -> Logger.flatDebug("After collecting: "+t1.toString()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { arrayList -> viewState.setContent(arrayList.toTypedArray()) }
                .let { attachDisposable(it) }
         */
        SearchFieldNotifier.searchField
                .flatMap { key ->
                    CharitiesProvider.requestCharitiesAsArray(key)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewState.setContent(it) }
                .let { attachDisposable(it) }
    }

}