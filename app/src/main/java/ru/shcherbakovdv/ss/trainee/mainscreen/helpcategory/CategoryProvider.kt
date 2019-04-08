package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.dataclasses.Category
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import java.io.File
import java.io.FileReader

object CategoryProvider {

    fun requestCategoriesFile() : Observable<File> {

        val CategoryJson = File("/sdcard/Download/categories.json")
        var categories = emptyArray<Category>()
        return Observable.just(CategoryJson)
    }
}