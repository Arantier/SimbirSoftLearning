package ru.shcherbakovdv.ss.trainee.eventscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.GsonBuilder
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.content_event_screen_images.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.utilites.DateUtility
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.LocalDateJsonDesrializer
import ru.shcherbakovdv.ss.trainee.utilites.LocaleStringManager.getLocaleQuantityString
import java.util.regex.Pattern

class EventActivity : AppCompatActivity() {

    private lateinit var event: CharityEvent

    private fun fillScreen(){
        toolbarTitle.text = event.title
        textTitle.text = event.title
        val restDays = DateUtility.daysRest(event.endDate)
        textEventItemDate.text = String.format(textEventItemDate.text.toString(),
                getLocaleQuantityString(this, R.plurals.event_date_expiration, restDays),
                event.startDate.format(DateTimeFormatter.ofPattern("dd.MM")),
                event.endDate.format(DateTimeFormatter.ofPattern("dd.MM"))
        )
        textOrganisationName.text = event.organisation.name
        textOrganisationAddress.text = event.organisation.address
        textOrganisationPhones.text = event.organisation.phones.joinToString("\n")

        textMessage.apply {
            //Всё ещё не работает, причина неизвестна
            text = getString(R.string.event_screen_organisation_message)
            val p = Pattern.compile("Напишите нам!")
            Linkify.addLinks(this,p,event.organisation.email)
        }
        textHyperlink.apply {
            val siteString = getString(R.string.event_screen_organisation_site)
            val siteUrlString = SpannableString(siteString).apply {
                setSpan(URLSpan(event.organisation.site), 0, siteString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            text = siteUrlString
            movementMethod = LinkMovementMethod.getInstance()
        }
        textDescription.text = event.description
        ImageProvider.apply {
            loadImage(event.picturesUrlArray[0], imageBig)
            loadImage(event.picturesUrlArray[1], imageSmallTop)
            loadImage(event.picturesUrlArray[2], imageSmallBottom)
        }
        val donatorsToShow = Math.min(event.donatorsPicturesUrlArray.size, 5)
        val pictureUrlArray = event.donatorsPicturesUrlArray.slice(0 until donatorsToShow)
        for (i in 0 until pictureUrlArray.size) {
            val donatorPhotoView = CircleImageView(this).apply {
                val donatorPhotoSize = resources.getDimensionPixelSize(R.dimen.donator_picture_size)
                val sizeParams = ViewGroup.LayoutParams(donatorPhotoSize, donatorPhotoSize)
                layoutParams = ViewGroup.MarginLayoutParams(sizeParams).apply {
                    setMargins(
                            if (i == 0) 0 else resources.getDimensionPixelSize(R.dimen.donator_picture_start_margin),
                            resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical),
                            0,
                            resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical)
                    )
                }
                elevation = i.toFloat()
            }
            ImageProvider.loadImage(pictureUrlArray[i], donatorPhotoView)
            layoutDonatorsBar.addView(donatorPhotoView)
        }
        if (event.donatorsPicturesUrlArray.size - donatorsToShow > 0) {
            val donatorCountView = TextView(this).apply {
                val sizeParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
                layoutParams = ViewGroup.MarginLayoutParams(sizeParams).apply {
                    setMargins(
                            resources.getDimensionPixelSize(R.dimen.donator_counter_margin_start),
                            0,
                            0,
                            0
                    )
                }
                gravity = Gravity.CENTER_VERTICAL
                setTextColor(resources.getColor(R.color.donatorsTextColor))
                setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.event_screen_donators_text_size))
                text = "+${event.donatorsPicturesUrlArray.size - donatorsToShow}"
            }
            layoutDonatorsBar.addView(donatorCountView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        event = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateJsonDesrializer())
                .create()
                .fromJson(intent.getStringExtra(EVENT_JSON), CharityEvent::class.java)
        fillScreen()
    }

    companion object {
        const val EVENT_JSON = "eventjson"
    }

}
