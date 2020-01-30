package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.data.RealmCategory

object CategoriesProvider {

    var categories: Array<Category>? = null
    lateinit var realm: Realm

    fun requestCategoriesFile(): Flowable<Array<Category>> {
        realm = Realm.getDefaultInstance()
        return realm.where(RealmCategory::class.java)
                .findAllAsync()
                .asFlowable()
                .flatMap {
                    realm.close()
                    val categoriesList = ArrayList<Category>()
                    for (realmCategory in it) {
                        val category = Category(
                                realmCategory?.id ?: 0,
                                realmCategory.name ?: "",
                                realmCategory.pictureUrl ?: ""
                        )
                        categoriesList.add(category)
                    }
                    categories = categoriesList.toTypedArray()
                    categories?.let {
                        Flowable.just(it)
                    } ?: throw IllegalStateException("Empty categories")
                }
                .observeOn(AndroidSchedulers.mainThread())
    }
}