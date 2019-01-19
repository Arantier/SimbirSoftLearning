package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    fun itemSelected(item: MenuItem): Boolean {
        return if (item.isChecked) {
            false
        } else {
            viewState.selectItem(item)
            true
        }
    }
}