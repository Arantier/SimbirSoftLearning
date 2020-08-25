package ru.shcherbakovdv.ss.trainee.data

import org.threeten.bp.LocalDate
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

data class Charity(
    val categoryId: Int,
    val title: String,
    val description: String,
    val picturesUrls: Array<String>,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val organisationId: Int,
    val donatorsPicturesUrls: Array<String>?
) {
    fun toRealmCharity() = RealmCharity(
        categoryId,
        title,
        description,
        JsonUtils.toJson(picturesUrls),
        JsonUtils.toJson(startDate),
        JsonUtils.toJson(endDate),
        organisationId,
        donatorsPicturesUrls?.let { JsonUtils.toJson(it) } ?: "[]"
    )
}