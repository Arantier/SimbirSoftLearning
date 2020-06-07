package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.data.RealmOrganisation
import java.util.*
import kotlin.collections.ArrayList

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

    fun requestRandomGeneratedOrganisations(): Array<Organisation> {
        val organisations = ArrayList<Organisation>()
        val random = Random()
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            organisations.add(
                Organisation(
                    random.nextInt(),
                    string,
                    "Point Nemo",
                    arrayOf("8-800-555-35-35", "1-234-567-89-10"),
                    "google@google.com",
                    "127.0.0.1"
                )
            )
        }
        return organisations.toTypedArray()
    }

    fun getOrganisationById(id: Int) = organisationsSingle
        .map { organisations -> organisations.first { it.id == id } }

}