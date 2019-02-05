package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.Organisation
import java.util.*

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }

    fun findContent(key:String?){
        Interactor.findContent(key)
    }
}