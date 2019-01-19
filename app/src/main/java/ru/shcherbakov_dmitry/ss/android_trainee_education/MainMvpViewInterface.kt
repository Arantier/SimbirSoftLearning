package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.view.MenuItem
import com.arellomobile.mvp.MvpView

interface MainMvpViewInterface : MvpView{

    fun selectItem(item: MenuItem)

}