package ru.shcherbakovdv.ss.trainee.eventscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.content_event_screen_images.*
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import ru.shcherbakovdv.ss.trainee.utilites.LocaleStringManager.getLocaleQuantityString

import ru.shcherbakovdv.ss.trainee.R
import ru.shcherbakovdv.ss.trainee.dataclasses.CharityEvent
import ru.shcherbakovdv.ss.trainee.dataclasses.Organisation
import ru.shcherbakovdv.ss.trainee.utilites.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.Logger

class EventActivity : AppCompatActivity() {

    lateinit var event: CharityEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        val builder = GsonBuilder().apply {

        }
        val gson = builder.create()
        event = CharityEvent(
                intent.getStringExtra(EVENT_TITLE),
                intent.getStringExtra(EVENT_DESCRIPTION),
                intent.getStringArrayExtra(EVENT_PICTURES_ARRAY),
                LocalDate.parse(intent.getStringExtra(EVENT_START_DATE)),
                LocalDate.parse(intent.getStringExtra(EVENT_END_DATE)),
                gson.fromJson(intent.getStringExtra(EVENT_ORGANISATION), Organisation::class.java),
                intent.getStringArrayExtra(EVENT_DONATORS_PICTURES_ARRAY))
    }

    override fun onStart() {
        super.onStart()
        toolbarTitle.text = event.title
        textTitle.text = event.title
        val restDays = Period.between(event.endDate, LocalDate.now()).days
        textEventItemDate.text = String.format(textEventItemDate.text.toString(),
                getLocaleQuantityString(this, R.plurals.event_date_expiration, restDays),
                event.startDate.format(DateTimeFormatter.ofPattern("dd.MM")),
                event.endDate.format(DateTimeFormatter.ofPattern("dd.MM"))
        )
        textOrganisationName.text = event.organisation.name
        textOrganisationAddress.text = event.organisation.address
        textOrganisationPhones.text = event.organisation.phones.contentToString()
        textDescription.text = event.description
        ImageProvider.apply {
            loadImage(event.picturesUrlArray[0], imageBig)
            loadImage(event.picturesUrlArray[1], imageSmallTop)
            loadImage(event.picturesUrlArray[2], imageSmallBottom)
        }
        val donatorsToShow = Math.min(event.donatorsPicturesUrlArray.size, 5)
        val pictureUrlArray = event.donatorsPicturesUrlArray.slice(0 until donatorsToShow)
        for (i in 0 until pictureUrlArray.size) {
            val donatorPhotoView = ImageView(this).apply {
                val donatorPhotoSize = resources.getDimensionPixelSize(R.dimen.donator_picture_size)
                val sizeParams = ViewGroup.LayoutParams(donatorPhotoSize, donatorPhotoSize)
                layoutParams = ViewGroup.MarginLayoutParams(sizeParams).apply {
                    setMargins(
                            if (i ==0) 0 else resources.getDimensionPixelSize(R.dimen.donator_picture_start_margin),
                            resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical),
                            0,
                            resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical)
                    )
                }
            }
            ImageProvider.loadRoundedImage(pictureUrlArray[i], donatorPhotoView)
            layoutDonatorsBar.addView(donatorPhotoView)
        }
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
            setTextSize(TypedValue.COMPLEX_UNIT_PX,resources.getDimension(R.dimen.event_screen_donators_text_size))
            text = "+${event.donatorsPicturesUrlArray.size-donatorsToShow}"
        }
        layoutDonatorsBar.addView(donatorCountView)
    }

    companion object {
        const val EVENT_TITLE = "title"
        const val EVENT_DESCRIPTION = "description"
        const val EVENT_PICTURES_ARRAY = "pic_array"
        const val EVENT_START_DATE = "start_date"
        const val EVENT_END_DATE = "end_date"
        const val EVENT_ORGANISATION = "organisation"
        const val EVENT_DONATORS_PICTURES_ARRAY = "donators_pic_array"
    }

}
