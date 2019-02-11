package ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovDmitry.ss.androidTraineeEducation.R
import ru.shcherbakovDmitry.ss.androidTraineeEducation.mainscreen.search.SearchFieldNotifier

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }
}