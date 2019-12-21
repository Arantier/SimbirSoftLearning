package ru.shcherbakovdv.ss.trainee.data_providers

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data_classes.Category
import java.io.File
import java.io.FileReader

object CategoriesProvider {

    var categories: Array<Category>? = null

    fun requestCategoriesFile(): Observable<Array<Category>> {
        var categoryJson = File("/sdcard/Download/categories.json")
        if (!categoryJson.exists()) {
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