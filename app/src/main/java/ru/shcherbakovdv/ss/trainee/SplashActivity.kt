package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.providers.CategoriesProvider
import ru.shcherbakovdv.ss.trainee.data.providers.CharitiesProvider
import ru.shcherbakovdv.ss.trainee.data.providers.OrganisationsProvider
import ru.shcherbakovdv.ss.trainee.utilites.extensions.saveToRealm

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private val ERROR_TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)

        Completable.create {
            Realm.getDefaultInstance()
                .apply {
                    executeTransaction { it.deleteAll() }
                    close()
                }
        }.subscribeOn(Schedulers.io()).subscribe()

        updateData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { startActivity(Intent(this, MainActivity::class.java)) },
                { error ->
                    showErrorMessage(error)
                }
            )

    }

    private fun updateData(): Completable {
        val networkCallback = NetworkCallback.newInstance(this)
        val categoriesCompletable = CategoriesProvider.loadRealmCategoriesFromNet()
            .saveToRealm()
            .let { Completable.fromObservable(it) }
        val organisationsCompletable =
            OrganisationsProvider.loadRealmOrganisatonsFromNet()
                .saveToRealm()
                .let { Completable.fromObservable(it) }
        val charitiesCompletable = CharitiesProvider.loadRealmCharitiesFromNetWithRetrofit()
            .saveToRealm()
            .let { Completable.fromObservable(it) }

        return categoriesCompletable
            .andThen(organisationsCompletable)
            .andThen(charitiesCompletable)
            .materialize<Void>()
            .mergeWith(networkCallback.networkLiveState
                .map { if (!it) throw IllegalStateException("Net unavailable") }
                .ignoreElements()
                .materialize())
            .dematerialize { it }.ignoreElements()
    }


    private fun showErrorMessage(throwable: Throwable) {
        // TODO: раздели ошибки по категориям и сделай отдельный вывод для каждой
        Log.e(ERROR_TAG, throwable.message)
        // TODO: перенеси это в другое место
        AlertDialog.Builder(this)
            .setTitle(R.string.title_error)
            .setMessage(getString(R.string.msg_load_error))
            .setNegativeButton(R.string.button_close) { dialog, id ->
                finish()
            }
            .setOnCancelListener {
                finish()
            }
            .create()
            .show()

    }
}
