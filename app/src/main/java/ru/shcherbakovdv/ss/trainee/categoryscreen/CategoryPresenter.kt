package ru.shcherbakovdv.ss.trainee.categoryscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.shcherbakovdv.ss.trainee.dataclasses.EventProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryMvpView>() {

    fun requestEvents(categoryId: Int) {
        viewState.setLoadingState()
        if (categoryId==-1){
            viewState.setErrorState()
            return
        }
        EventProvider.requestAllEvents()
                .subscribe(({ array ->
                    array.filter { it.categoryId == categoryId }
                            .apply {
                                viewState.updateList(this.toTypedArray())
                            }
                }), ({ throwable ->
                    Logger.flatError(throwable.toString())
                    viewState.setErrorState()
                }))
    }
}