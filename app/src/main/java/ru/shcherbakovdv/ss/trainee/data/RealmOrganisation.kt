package ru.shcherbakovdv.ss.trainee.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

open class RealmOrganisation(
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    var address: String? = null,
    var phones: String? = null,
    var email: String? = null,
    var site: String? = null
) : RealmObject() {
    fun toOrganisation() = try {
        Organisation(
            id!!,
            name!!,
            address!!,
            JsonUtils.fromJson(phones, Array<String>::class.java)!!,
            email!!,
            site!!
        )
    } catch (exception: NullPointerException) {
        null
    }
}

