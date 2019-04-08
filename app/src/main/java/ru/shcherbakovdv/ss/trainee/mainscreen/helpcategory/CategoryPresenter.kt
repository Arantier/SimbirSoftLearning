package ru.shcherbakovdv.ss.trainee.mainscreen.helpcategory

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.shcherbakovdv.ss.trainee.dataclasses.Category
import ru.shcherbakovdv.ss.trainee.utilites.Logger
import java.io.FileReader

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryMvpView>() {

    fun requestCategories() {
        viewState.setLoadingState()
        CategoryProvider.requestCategoriesFile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    val gson = Gson()
                    val gsonBuilder = gson.newBuilder()
                    viewState.updateList(gson.fromJson<Array<Category>>(processedString, Array<Category>::class.java))
                }, ({ throwable ->
                    Logger.flatError(throwable.toString())
                    viewState.setErrorState()
                }))
    }
}