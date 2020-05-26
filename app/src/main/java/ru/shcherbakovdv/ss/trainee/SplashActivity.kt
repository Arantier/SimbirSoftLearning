package ru.shcherbakovdv.ss.trainee

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.RealmCategory
import ru.shcherbakovdv.ss.trainee.data.RealmCharity
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils
import java.io.IOException
import java.io.InputStream

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private lateinit var realm: Realm

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)
        realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            realm.deleteAll()
        }

        val isStorageAvailable = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
        if (isStorageAvailable) {
            disposable = loadAndSave()
                .subscribe({
                    startActivity(Intent(this, MainActivity::class.java))
                }, { t -> showErrorMessage() })
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    disposable = loadAndSave()
                        .subscribe({
                            startActivity(Intent(this, MainActivity::class.java))
                        }, { t -> showErrorMessage() })
                } else {
                    Log.e(LOG_TAG, "Storage permission wasn't granted")
                    showErrorMessage()
                }
            }
        }
    }

    private fun showErrorMessage() {
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

    fun getGithubRetrofit() = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun loadData(): Single<Pair<Array<RealmCategory>, Array<RealmCharity>>> =
        Single.create<Pair<InputStream, InputStream>> {
            try {
                it.onSuccess(
                    Pair(
                        assets.open("categories.json"),
                        assets.open("charities.json")
                    )
                )
            } catch (exception: IOException) {
                it.onError(exception)
            }
        }.flatMap { filePair ->
            val categoriesData = filePair.first.let {
                val size = it.available()
                val buffer = ByteArray(size)
                it.read(buffer)
                it.close()
                String(buffer)
            }

            val charitiesData = filePair.second.let {
                val size = it.available()
                val buffer = ByteArray(size)
                it.read(buffer)
                it.close()
                String(buffer)
            }

            val categoriesArray = Gson().fromJson<Array<RealmCategory>>(
                categoriesData,
                Array<RealmCategory>::class.java
            )
            val charitiesArray = JsonUtils.gson.fromJson(charitiesData, Array<Charity>::class.java)
                .let { array ->
                    val realmCharitiesList = ArrayList<RealmCharity>()
                    for (charity in array) {
                        realmCharitiesList.add(
                            RealmCharity(
                                charity.categoryId,
                                charity.title,
                                charity.description,
                                JsonUtils.toJson(charity.picturesUrls),
                                charity.startDate.format(DateTimeFormatter.ISO_DATE),
                                charity.endDate.format(DateTimeFormatter.ISO_DATE),
                                JsonUtils.toJson(charity.organisation),
                                JsonUtils.toJson(charity.donatorsPicturesUrls)
                            )
                        )
                    }
                    realmCharitiesList.toTypedArray()
                }

            Single.just(Pair(categoriesArray, charitiesArray))
        }

    private fun saveDataToRealm(pair: Pair<Array<RealmCategory>, Array<RealmCharity>>): Single<Int> {
        val categoriesArray = pair.first
        val charitiesArray = pair.second

        realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        for (category in categoriesArray) {
            realm.copyToRealm(category)
        }

        for (charity in charitiesArray) {
            realm.copyToRealm(charity)
        }

        realm.commitTransaction()
        realm.close()

        return Single.just(1)
    }

    private fun loadAndSave(): Single<Int> =
        loadData().flatMap(this::saveDataToRealm)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    companion object {
        const val LOG_TAG = "Splash Activity"

        const val STORAGE_REQUEST_CODE = 1
    }
}
