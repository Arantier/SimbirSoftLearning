package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import org.threeten.bp.LocalDate
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.data.RealmCharity
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

object CharitiesProvider {

    var charities: Array<Charity>? = null
    lateinit var realm: Realm

    fun requestAllCharities(): Flowable<Array<Charity>> {
        realm = Realm.getDefaultInstance()
        return realm.where(RealmCharity::class.java)
                .findAllAsync()
                .asFlowable()
                .flatMap {
                    realm.close()
                    val charitiesList = ArrayList<Charity>()
                    for (realmCharity in it) {
                        realmCharity.apply {
                            val charity = Charity(
                                    categoryId ?: 0,
                                    title ?: "",
                                    description ?: "",
                                    JsonUtils.fromJson(picturesUrlArray, Array<String>::class.java)
                                            ?: throw java.lang.IllegalStateException("Bad charity field: pictures url array is null"),
                                    JsonUtils.fromJson(startDate, LocalDate::class.java)
                                            ?: throw java.lang.IllegalStateException("Bad field: start date is null"),
                                    JsonUtils.fromJson(endDate, LocalDate::class.java)
                                            ?: throw java.lang.IllegalStateException("Bad field: end date is null"),
                                    JsonUtils.fromJson(organisation, Organisation::class.java)
                                            ?: throw java.lang.IllegalStateException("Bad field: organisation is null"),
                                    JsonUtils.fromJson(donatorsPicturesUrlArray, Array<String>::class.java)
                                            ?: throw java.lang.IllegalStateException("Bad field: donators pictures url array is null")
                            )
                            charitiesList.add(charity)
                        }
                    }
                    charities = charitiesList.toTypedArray()
                    charities?.let {
                        Flowable.just(it)
                    } ?: throw IllegalStateException("Empty charities")
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestCharities(key: String): Flowable<Array<Charity>> {
        if (charities != null) {
            return Flowable.just(charities!!.filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }.toTypedArray())
        } else {
            return requestAllCharities()
                    .map { array: Array<Charity> ->
                        array.filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }.toTypedArray()
                    }
        }
    }

}