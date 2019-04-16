package ru.shcherbakovdv.ss.trainee.dataclasses

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileReader

object CategoryProvider {

    fun requestCategoriesFile() : Observable<Array<Category>> {
        var categoryJson = File("/sdcard/Download/categories.json")
        if (!categoryJson.exists()){
            categoryJson = File("/storage/emulated/0/Download/categories.json")
        }
        return Observable.just(categoryJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Observable.just(Gson().fromJson<Array<Category>>(processedString, Array<Category>::class.java))
                }
    }
}