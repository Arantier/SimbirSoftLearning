package ru.shcherbakovdv.ss.trainee.data.providers

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.shcherbakovdv.ss.trainee.data.Category
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils

interface DatabaseService {

    @GET("categories.json")
    fun getCategories(): Single<Array<Category>>

    @GET("charities.json")
    fun getCharities(): Single<Array<Charity>>

    @GET("organisations.json")
    fun getOrganisations(): Single<Array<Organisation>>

    companion object {
        fun getApi() = Retrofit.Builder()
            .baseUrl("https://ss-shcherbakov-learning.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create(JsonUtils.gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(DatabaseService::class.java)
    }
}
