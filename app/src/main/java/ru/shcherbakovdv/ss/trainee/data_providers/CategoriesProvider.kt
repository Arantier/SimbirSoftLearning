package ru.shcherbakovdv.ss.trainee.data_providers

import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.data.Category
import java.io.File
import java.io.FileReader

object CategoriesProvider {

    var categories: Array<Category>? = null

    fun requestCategoriesFile(): Single<Array<Category>> {
        // TODO: Замени на выдачу файлов из бд
        var categoryJson = File("/sdcard/Download/categories.json")
        if (!categoryJson.exists()) {
            categoryJson = File("/storage/emulated/0/Download/categories.json")
        }
        return Single.just(categoryJson)
                .subscribeOn(Schedulers.io())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Single.just(Gson().fromJson<Array<Category>>(processedString, Array<Category>::class.java))
                }
                .observeOn(AndroidSchedulers.mainThread())
    }
}