package ru.shcherbakovdv.ss.trainee.dataclasses

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import java.io.File
import java.io.FileReader

object EventProvider {

    fun requestAllEvents(): Single<Array<CharityEvent>> {
        var eventsJson = File("/sdcard/Download/events.json")
        if (!eventsJson.exists()) {
            eventsJson = File("/storage/emulated/0/Download/events.json")
        }
        return Single.just(eventsJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Single.just(JsonUtilities.gson.fromJson(processedString, Array<CharityEvent>::class.java))
                }
    }

    fun requestEvents(key: String): Single<Array<CharityEvent>> {
        var eventsJson = File("/sdcard/Download/events.json")
        if (!eventsJson.exists()) {
            eventsJson = File("/storage/emulated/0/Download/events.json")
        }
        return Single.just(eventsJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Single.just(JsonUtilities.gson.fromJson(processedString, Array<CharityEvent>::class.java)
                            .filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }
                            .toTypedArray())
                }
    }

}