package ru.shcherbakovdv.ss.trainee.data

data class Category(
    val id: Int,
    val name: String,
    val pictureUrl: String
) {
    fun toRealmCategory(): RealmCategory = RealmCategory(id, name, pictureUrl)
}