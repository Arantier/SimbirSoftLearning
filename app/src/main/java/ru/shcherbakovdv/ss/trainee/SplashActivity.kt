package ru.shcherbakovdv.ss.trainee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmObject
import ru.shcherbakovdv.ss.trainee.data.NetworkCallback
import ru.shcherbakovdv.ss.trainee.data.RealmCategory
import ru.shcherbakovdv.ss.trainee.data.RealmCharity

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
            .subscribeOn(Schedulers.io())
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
        val categoriesCompletable = loadCategories()
            .saveToRealm()
            .let { Completable.fromObservable(it) }
        val charitiesCompletable = loadCharities()
            .saveToRealm()
            .let { Completable.fromObservable(it) }

        return categoriesCompletable
            .andThen(charitiesCompletable)
            .materialize<Void>()
            .mergeWith(networkCallback.networkLiveState
                .map { if (!it) throw IllegalStateException("Net unavailable") }
                .ignoreElements()
                .materialize())
            .dematerialize { it }.ignoreElements()
    }

    private fun loadCategories(): Observable<RealmCategory> =
        Observable.create<RealmCategory> { emitter ->
            FirebaseDatabase.getInstance()
                .getReference("categories")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            it.getValue(RealmCategory::class.java)
                                ?.let { emitter.onNext(it) }
                                ?: emitter.onError(NullPointerException("Null pointer exception occured while retrieving data from net"))
                        }
                        emitter.onComplete()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onError(error.toException())
                    }
                })
        }

    private fun loadCharities(): Observable<RealmCharity> =
        Observable.create { emitter ->
            FirebaseDatabase.getInstance()
                .getReference("categories")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            it.getValue(RealmCharity::class.java)
                                ?.let { emitter.onNext(it) }
                                ?: emitter.onError(NullPointerException("Null pointer exception occured while retrieving data from net"))
                        }
                        emitter.onComplete()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onError(error.toException())
                    }
                })
        }

    private fun <T : RealmObject> Observable<out T>.saveToRealm(): Observable<out T> {
        var realm: Realm? = null
        return this
            .observeOn(Schedulers.io())
            .doOnEach {
                if (realm == null) {
                    realm = Realm.getDefaultInstance()
                    realm?.beginTransaction()
                }
            }
            .doOnNext {
                realm?.copyToRealm(it)
            }
            .doOnComplete { realm?.commitTransaction() }
            .doOnError { realm?.cancelTransaction() }
            .doOnTerminate {
                realm?.close()
            }.observeOn(AndroidSchedulers.mainThread())
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
