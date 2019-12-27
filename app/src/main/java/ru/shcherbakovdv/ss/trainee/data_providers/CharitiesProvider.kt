package ru.shcherbakovdv.ss.trainee.data_providers

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import java.io.File
import java.io.FileReader

object CharitiesProvider {

    var currentCharities: Array<Charity>? = null

    fun requestAllCharities(): Single<Array<Charity>> {
        var eventsJson = File("/sdcard/Download/events.json")
        if (!eventsJson.exists()) {
            eventsJson = File("/storage/emulated/0/Download/events.json")
        }
        return Single.just(eventsJson)
                .subscribeOn(Schedulers.io())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Single.just(JsonUtilities.gson.fromJson(processedString, Array<Charity>::class.java))
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestCharities(key: String): Single<Array<Charity>> {
        if (currentCharities != null) {
            return Single.just(currentCharities!!.filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }.toTypedArray())
        } else {
            return requestAllCharities()
                    .map { array: Array<Charity> ->
                        array.filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }.toTypedArray()
                    }
        }
    }

}