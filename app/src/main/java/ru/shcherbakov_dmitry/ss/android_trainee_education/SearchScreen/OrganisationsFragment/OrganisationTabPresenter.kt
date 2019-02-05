package ru.shcherbakov_dmitry.ss.android_trainee_education.SearchScreen.OrganisationsFragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakov_dmitry.ss.android_trainee_education.Interactor
import ru.shcherbakov_dmitry.ss.android_trainee_education.Observer

@InjectViewState
class OrganisationTabPresenter : MvpPresenter<OrganisationTabMvpView>(), Observer {

    override var isActive = false

    init {
        Interactor.attachObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Interactor.detachObserver(this)
    }

    fun requestContent() = requestContent(null)

    override fun requestContent(key: String?) {
        val organisationArray = OrganisationTabInteractor.requestListOfOrganisations(key)
        viewState.setContent(organisationArray)
    }

    fun requestOrganisationActivity(organisationId: Int) {
        //TODO: Сделать потом
    }
}