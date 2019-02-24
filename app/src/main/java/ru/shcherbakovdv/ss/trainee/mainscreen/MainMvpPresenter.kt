package ru.shcherbakovdv.ss.trainee.mainscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.R

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
        }
}