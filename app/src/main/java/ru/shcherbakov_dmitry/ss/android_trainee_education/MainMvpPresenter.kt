package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainMvpPresenter : MvpPresenter<MainMvpViewInterface>() {

    var currentScreenID = R.id.bottom_help
        set(id) {
            field = id
            viewState.selectScreen(id)
            when (id){
                R.id.bottom_news -> Log.i("Pres","News")
                R.id.bottom_search -> Log.i("Pres","Search")
                R.id.bottom_help -> Log.i("Pres","Help")
                R.id.bottom_history -> Log.i("Pres","History")
                R.id.bottom_profile -> Log.i("Pres","Profile")
            }
        }
    get(){
        when (field){
            R.id.bottom_news -> Log.i("Pres","News")
            R.id.bottom_search -> Log.i("Pres","Search")
            R.id.bottom_help -> Log.i("Pres","Help")
            R.id.bottom_history -> Log.i("Pres","History")
            R.id.bottom_profile -> Log.i("Pres","Profile")
        }
        return field
    }

}