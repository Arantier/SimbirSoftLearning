package ru.shcherbakovDmitry.ss.androidTraineeEducation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }

    fun findContent(key:String?){
        if (key != null) {
            SearchFieldNotifier.findContent(key)
        }
    }
}