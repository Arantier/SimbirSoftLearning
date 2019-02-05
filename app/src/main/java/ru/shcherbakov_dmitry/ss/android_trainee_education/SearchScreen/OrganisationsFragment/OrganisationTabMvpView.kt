package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.OrganisationsFragment

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.CharityEvent
import ru.shcherbakov_dmitry.ss.android_trainee_education.ModelFiles.Organisation

@StateStrategyType(AddToEndSingleStrategy :: class)
interface OrganisationTabMvpView : MvpView {
    fun setContent(organisationArray : Array<Organisation>)

    fun startItemActivity(eventArray:Array<CharityEvent>)
}