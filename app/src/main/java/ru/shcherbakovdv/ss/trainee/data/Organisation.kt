package ru.shcherbakovdv.ss.trainee.data

import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

data class Organisation(
    val id: Int,
    val name: String,
    val address: String,
    val phones: Array<String>,
    val email: String,
    val site: String
) {
    fun toRealmOrganisation() =
        RealmOrganisation(id, name, address, JsonUtils.toJson(phones), email, site)
}