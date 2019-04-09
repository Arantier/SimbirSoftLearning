package ru.shcherbakovdv.ss.trainee.dataclasses

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate
import ru.shcherbakovdv.ss.trainee.utilites.LocalDateJsonDesrializer
import java.io.File
import java.io.FileReader
import java.lang.Exception
import java.util.*

object EventProvider {

    fun requestTestEventArray(): Array<CharityEvent> {
        val eventArray = ArrayList<CharityEvent>()
        val random = Random()
        val numberOfElements = random.nextInt(32)
        for (i in 1..numberOfElements) {
            var string = ""
            val stringLength = random.nextInt(32)
            for (j in 1..stringLength) {
                string += random.nextInt(10).toString()
            }
            eventArray.add(CharityEvent(0, string,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    arrayOf("https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/89D9586D-AA7B-48C8-BDE4-251613E56E55.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/B5F876E6-33CF-40A0-83C3-245C4DE52FF5.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/BF241106-312F-430A-B8B1-A310D0283B61.png"),
                    LocalDate.parse("2019-01-01"),
                    LocalDate.parse("2019-12-31"),
                    Organisation("Organisation",
                            "Point Nemo",
                            arrayOf("+8 (800) 555 35-35", "+1 (234) 567 89-10"),
                            "google@google.com",
                            "127.0.0.1"),
                    arrayOf("https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/DAD0F219-1467-484D-A881-8EB9A32431A3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/D47394DD-4E5A-4FAC-891B-C67524C727E0.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/221E2EC0-0074-4A53-A60D-F06685AF2D4F.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/619A9895-9757-40AC-83B5-14DB17FEAD9C.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png",
                            "https://cdn.zeplin.io/5a8295e8de62056425a09dbc/assets/04C849CF-F0C9-423F-9117-6F3EA6E9CEF3.png")))
        }
        return eventArray.toTypedArray()
    }

    fun requestAllEvents(): Observable<Array<CharityEvent>> {
        val eventsJson = File("/sdcard/Download/events.json")
        return Observable.just(eventsJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    //Код снизу нужен для теста с моего телефона
                    lateinit var fileReader : FileReader
                    try {
                        fileReader = FileReader(file)
                    } catch(e : Exception){
                        fileReader = FileReader("/storage/emulated/0/Download/events.json")
                    }
                    val processedString = fileReader.readText()
                    fileReader.close()
                    val gson = GsonBuilder()
                            .registerTypeAdapter(LocalDate::class.java,LocalDateJsonDesrializer())
                            .create()
                    Observable.just(gson.fromJson(processedString, Array<CharityEvent>::class.java))
                }
    }

    fun requestEvents(key:String): Observable<Array<CharityEvent>> {
        val eventsJson = File("/sdcard/Download/events.json")
        return Observable.just(eventsJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { file ->
                    val fileReader = FileReader(file)
                    val processedString = fileReader.readText()
                    fileReader.close()
                    val gson = GsonBuilder()
                            .registerTypeAdapter(LocalDate::class.java,LocalDateJsonDesrializer())
                            .create()
                    Observable.just(gson.fromJson(processedString, Array<CharityEvent>::class.java)
                            .filter { it.title.toLowerCase().contains(key.toLowerCase()) || it.description.toLowerCase().contains(key.toLowerCase()) }
                            .toTypedArray())
                }
    }

}