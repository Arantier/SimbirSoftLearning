package ru.shcherbakovdv.ss.trainee.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmCategory(
        @PrimaryKey var id: Int? = null,
        var name: String? = null,
        var pictureUrl: String? = null
) : RealmObject()