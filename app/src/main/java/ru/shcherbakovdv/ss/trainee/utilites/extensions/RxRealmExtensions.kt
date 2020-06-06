package ru.shcherbakovdv.ss.trainee.utilites.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject

fun <T : RealmObject> Observable<out T>.saveToRealm(): Observable<out T> {
    var realm: Realm? = null
    return this
        .observeOn(Schedulers.io())
        .doOnEach {
            if (realm == null) {
                realm = Realm.getDefaultInstance()
                realm?.beginTransaction()
            }
        }
        .doOnNext {
            realm?.copyToRealm(it)
        }
        .doOnComplete { realm?.commitTransaction() }
        .doOnError { realm?.cancelTransaction() }
        .doOnTerminate {
            realm?.close()
        }.observeOn(AndroidSchedulers.mainThread())
}