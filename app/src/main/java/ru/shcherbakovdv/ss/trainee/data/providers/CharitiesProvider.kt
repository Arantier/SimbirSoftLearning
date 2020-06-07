package ru.shcherbakovdv.ss.trainee.data.providers

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    fun loadRealmCharitiesFromNetWithRetrofit() = DatabaseService.getApi()
        .getCharities()
        .flatMapObservable { Observable.fromIterable(it.asIterable()) }
        .map { it.toRealmCharity() }

    fun loadRealmCharitiesFromNet(): Observable<RealmCharity> =
        Observable.create { emitter ->
            FirebaseDatabase.getInstance()
                .getReference("categories")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            it.getValue(RealmCharity::class.java)
                                ?.let { emitter.onNext(it) }
                                ?: emitter.onError(NullPointerException("Null pointer exception occured while retrieving data from net"))
                        }
                        emitter.onComplete()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onError(error.toException())
                    }
                })
        }

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
                emptyArray<Charity>()
            } else {
                array.filter { charity ->
                    charity.title.contains(key) or charity.description.contains(key)
                }.toTypedArray()
            }
        }

}