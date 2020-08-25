package ru.shcherbakovdv.ss.trainee.data.providers

import android.util.Log
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.shcherbakovdv.ss.trainee.data.User
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

object UsersProvider {

    val USER_ERROR  = "User provider error"

    fun getUserById(id: String): Single<User> = Retrofit.Builder()
        .baseUrl("https://ss-shcherbakov-learning.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create(JsonUtils.gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(UserService::class.java)
        .getUserById(id)

    fun getUserVisuals(id: String): Single<Pair<String, String>> = Retrofit.Builder()
        .baseUrl("https://ss-shcherbakov-learning.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create(JsonUtils.gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(UserService::class.java)
        .let { service ->
            Singles.zip(
                service.getUserNameById(id),
                service.getUserPhotoUrlById(id)
            ) { name, photoUrl ->
                Pair(name, photoUrl) }
                .onErrorReturn {
                    Log.e(USER_ERROR, it.message)
                    Pair("","")
                }
        }
}