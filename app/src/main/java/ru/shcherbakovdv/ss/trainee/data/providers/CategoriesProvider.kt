package ru.shcherbakovdv.ss.trainee.data.providers

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

    fun loadRealmCategoriesFromNet() = DatabaseService.getApi()
        .getCategories()
        .flatMapObservable { Observable.fromIterable(it.asIterable()) }
        .map { it.toRealmCategory() }

    fun getCategoriesFromDatabase() = Single.defer {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(RealmCategory::class.java).findAll()
        realm.copyFromRealm(results)
            .mapNotNull { it.toCategory() }
            .toTypedArray()
            .let { array ->
                realm.close()
                categories = array
                Single.just(array)
            }
    }
}