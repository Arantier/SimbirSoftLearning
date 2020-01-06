package ru.shcherbakovdv.ss.trainee

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.RealmCategory
import ru.shcherbakovdv.ss.trainee.data.RealmCharity
import ru.shcherbakovdv.ss.trainee.utilites.JsonUtilities
import java.io.File
import java.io.FileReader


class SplashActivity : AppCompatActivity() {

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


        val isStorageAvailable = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if (isStorageAvailable) {
            disposable = loadAndSave()
                    .subscribe({
                        startActivity(Intent(this, MainActivity::class.java))
                    }, { t ->
                        Log.e(LOG_TAG, "Error occurred during loading data to database: $t")
                        startActivity(Intent(this, MainActivity::class.java))
                    })
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    disposable = loadAndSave()
                            .subscribe({
                                startActivity(Intent(this, MainActivity::class.java))
                            }, { t ->
                                Log.e(LOG_TAG, "Error occurred during loading data to database: $t")
                                startActivity(Intent(this, MainActivity::class.java))
                            })
                } else {
                    Log.e(LOG_TAG, "Storage permission wasn't granted")
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }

    fun loadData(): Single<Pair<Array<RealmCategory>, Array<RealmCharity>>> {
        val categoriesJson = File("/sdcard/Download/categories.json")
        val charitiesJson = File("/sdcard/Download/events.json")

        return Single.just(Pair(categoriesJson, charitiesJson))
                .flatMap { filePair ->
                    val categoriesFileReader = FileReader(filePair.first)
                    val categoriesData = categoriesFileReader.readText()
                    categoriesFileReader.close()

                    val charitiesFileReader = FileReader(filePair.second)
                    val charitiesData = charitiesFileReader.readText()
                    charitiesFileReader.close()

                    val categoriesArray = Gson().fromJson<Array<RealmCategory>>(categoriesData, Array<RealmCategory>::class.java)
                    val charitiesArray = JsonUtilities.gson.fromJson(charitiesData, Array<Charity>::class.java)
                            .let { array ->
                                val realmCharitiesList = ArrayList<RealmCharity>()
                                for (charity in array) {
                                    realmCharitiesList.add(RealmCharity(
                                            charity.categoryId,
                                            charity.title,
                                            charity.description,
                                            JsonUtilities.toJson(charity.picturesUrlArray),
                                            charity.startDate.format(DateTimeFormatter.ISO_DATE),
                                            charity.endDate.format(DateTimeFormatter.ISO_DATE),
                                            JsonUtilities.toJson(charity.organisation),
                                            JsonUtilities.toJson(charity.donatorsPicturesUrlArray)
                                    ))
                                }
                                realmCharitiesList.toTypedArray()
                            }

                    Single.just(Pair(categoriesArray, charitiesArray))
                }
    }

    fun saveDataToRealm(pair: Pair<Array<RealmCategory>, Array<RealmCharity>>): Single<Int> {
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

    fun loadAndSave(): Single<Int> =
            loadData()
                    .flatMap(::saveDataToRealm)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    companion object {
        const val LOG_TAG = "Splash Activity"

        const val STORAGE_REQUEST_CODE = 1
    }
}
