package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.shcherbakovdv.ss.trainee.data.User

interface UserService {

    @GET("users/{id}.json")
    fun getUserById(@Path("id") id: String) : Single<User>

    @GET("users/{id}/name.json")
    fun getUserNameById(@Path("id") id: String) : Single<String>

    @GET("users/{id}/photoUrl.json")
    fun getUserPhotoUrlById(@Path("id") id: String) : Single<String>
}