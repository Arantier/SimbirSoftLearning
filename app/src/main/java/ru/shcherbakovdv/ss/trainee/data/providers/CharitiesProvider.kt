package ru.shcherbakovdv.ss.trainee.data.providers

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.RealmCharity
import java.util.*

object CharitiesProvider {

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