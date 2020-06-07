package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.RealmCharity

object CharitiesProvider {

    private var charities: Array<Charity>? = null

    val charitiesSingle: Single<Array<Charity>>
        get() = charities?.let {
            Single.just(it)
        } ?: getCharitiesFromDatabase()

    fun loadRealmCharitiesFromNet() = DatabaseService.getApi()
        .getCharities()
        .flatMapObservable { Observable.fromIterable(it.asIterable()) }
        .map { it.toRealmCharity() }

    fun getCharitiesFromDatabase() = Single.defer {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(RealmCharity::class.java).findAll()
        realm.copyFromRealm(results)
            .mapNotNull { it.toCharity() }
            .toTypedArray()
            .let { array ->
                realm.close()
                charities = array
                Single.just(array)
            }
    }

    fun find(key: String) = charitiesSingle
        .map { array ->
            if (key.isEmpty()) {
                emptyArray()
            } else {
                array.filter { charity ->
                    charity.title.contains(key) or charity.description.contains(key)
                }.toTypedArray()
            }
        }

}