package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.data.RealmOrganisation

object OrganisationsProvider {

    private var organisations: Array<Organisation>? = null

    val organisationsSingle: Single<Array<Organisation>>
        get() = organisations?.let {
            Single.just(it)
        } ?: getOrganisationsFromDatabase()


    fun loadRealmOrganisatonsFromNet() = DatabaseService.getApi()
        .getOrganisations()
        .flatMapObservable { Observable.fromIterable(it.asIterable()) }
        .map { it.toRealmOrganisation() }


    fun getOrganisationsFromDatabase() = Single.defer {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(RealmOrganisation::class.java).findAll()
        realm.copyFromRealm(results)
            .mapNotNull { it.toOrganisation() }
            .toTypedArray()
            .let { array ->
                realm.close()
                organisations = array
                Single.just(array)
            }
    }

    fun getOrganisationById(id: Int) = organisationsSingle
        .map { organisations -> organisations.first { it.id == id } }

    fun find(key: String) = organisationsSingle.map { array ->
        if (key.isEmpty()) {
            emptyArray()
        } else {
            array.filter { organisation -> organisation.name.contains(key) }.toTypedArray()
        }
    }

}