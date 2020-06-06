package ru.shcherbakovdv.ss.trainee.data.providers

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.data.RealmCategory

object CategoriesProvider {

    private var categories: Array<Category>? = null

    val categoriesSingle: Single<Array<Category>>
        get() = categories?.let {
            Single.just(it)
        } ?: getCategoriesFromDatabase()

    fun loadRealmCategoriesFromNet(): Observable<RealmCategory> =
        Observable.create<RealmCategory> { emitter ->
            FirebaseDatabase.getInstance()
                .getReference("categories")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            it.getValue(RealmCategory::class.java)
                                ?.let { emitter.onNext(it) }
                                ?: emitter.onError(NullPointerException("Null pointer exception occurred while retrieving data from net"))
                        }
                        emitter.onComplete()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onError(error.toException())
                    }
                })
        }

    fun getCategoriesFromDatabase(): Single<Array<Category>> = Single.defer {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(RealmCategory::class.java).findAll()
        realm.copyFromRealm(results)
            .map { it.toCategory() }
            .filterNotNull()
            .toTypedArray()
            .let { array ->
                realm.close()
                categories = array
                Single.just(array)
            }
    }
}