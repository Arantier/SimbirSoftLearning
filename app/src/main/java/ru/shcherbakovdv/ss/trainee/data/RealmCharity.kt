package ru.shcherbakovdv.ss.trainee.data

import io.realm.RealmObject
import org.threeten.bp.LocalDate
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

open class RealmCharity(
        var categoryId: Int? = null,
        var title: String? = null,
        var description: String? = null,
        var picturesUrls: String? = null,
        var startDate: String? = null,
        var endDate: String? = null,
        var organisation: String? = null,
        var donatorsPicturesUrls: String? = null) : RealmObject() {

    fun toCharity() = Charity(
            categoryId ?: 0,
            title ?: "",
            description ?: "",
            JsonUtils.fromJson(picturesUrls, Array<String>::class.java)
                    ?: throw java.lang.IllegalStateException("Bad charity field: pictures url array is null"),
            JsonUtils.fromJson(startDate, LocalDate::class.java)
                    ?: throw java.lang.IllegalStateException("Bad field: start date is null"),
            JsonUtils.fromJson(endDate, LocalDate::class.java)
                    ?: throw java.lang.IllegalStateException("Bad field: end date is null"),
            JsonUtils.fromJson(organisation, Organisation::class.java)
                    ?: throw java.lang.IllegalStateException("Bad field: organisation is null"),
            JsonUtils.fromJson(donatorsPicturesUrls, Array<String>::class.java)
                    ?: throw java.lang.IllegalStateException("Bad field: donators pictures url array is null")
    )
}