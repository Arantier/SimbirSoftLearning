package ru.shcherbakovdv.ss.trainee.data

import io.realm.RealmObject

open class RealmCharity(
        var categoryId: Int? = null,
        var title: String? = null,
        var description: String? = null,
        var picturesUrls: String? = null,
        var startDate: String? = null,
        var endDate: String? = null,
        var organisation: String? = null,
        var donatorsPicturesUrls: String? = null) : RealmObject()