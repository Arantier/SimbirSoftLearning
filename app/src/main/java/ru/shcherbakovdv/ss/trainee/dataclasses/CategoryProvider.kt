package ru.shcherbakovdv.ss.trainee.dataclasses

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileReader
import java.lang.Exception

object CategoryProvider {

    fun requestCategoriesFile() : Observable<Array<Category>> {
        val categoryJson = File("/sdcard/Download/categories.json")
        return Observable.just(categoryJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    //Код снизу нужен для теста с моего телефона
                    lateinit var fileReader : FileReader
                    try {
                        fileReader = FileReader(file)
                    } catch(e : Exception){
                        fileReader = FileReader("/storage/emulated/0/Download/categories.json")
                    }
                    val processedString = fileReader.readText()
                    fileReader.close()
                    Observable.just(Gson().fromJson<Array<Category>>(processedString, Array<Category>::class.java))
                }
    }
}