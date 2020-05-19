package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Observable
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.RealmCharity
import java.util.*

object CharitiesProvider {

    fun requestAllCharities(): Observable<Charity> {
        val realm = Realm.getDefaultInstance()
        return realm.where(RealmCharity::class.java)
                .findAllAsync()
                .asFlowable()
                .toObservable()
                .flatMap {
                    Observable.fromIterable(it).apply { realm.close() }
                }.map { it.toCharity() }
    }

    fun requestAllCharitiesAsArray(): Observable<Array<Charity>> {
        val realm = Realm.getDefaultInstance()
        return realm.where(RealmCharity::class.java)
                .findAllAsync()
                .asFlowable()
                .toObservable()
                .flatMap {
                    it.map { it.toCharity() }
                            .toTypedArray()
                            .let {
                                realm.close()
                                if (it.isEmpty()) throw IllegalStateException("Empty charities")
                                Observable.just(it)
                            }
                }
    }

    fun requestCharities(key: String): Observable<Charity> {
        val realm = Realm.getDefaultInstance()
        return realm.where(RealmCharity::class.java)
                .contains("title", key.toLowerCase(Locale.ROOT))
                .or()
                .contains("description", key.toLowerCase(Locale.ROOT))
                .findAllAsync()
                .asFlowable()
                .toObservable()
                .flatMap {
                    Observable.fromIterable(it)
                }.map { it.toCharity() }
    }

    fun requestCharitiesAsArray(key: String): Observable<Array<Charity>> {
        val realm = Realm.getDefaultInstance()
        return realm.where(RealmCharity::class.java)
                .contains("title", key.toLowerCase(Locale.ROOT))
                .or()
                .contains("description", key.toLowerCase(Locale.ROOT))
                .findAllAsync()
                .asFlowable()
                .toObservable()
                .flatMap { results ->
                    results.map { it.toCharity() }
                            .toTypedArray()
                            .let { array ->
                                realm.close()
                                if (array.isEmpty()) throw IllegalStateException("Empty charities")
                                Observable.just(array)
                            }
                }
    }

}