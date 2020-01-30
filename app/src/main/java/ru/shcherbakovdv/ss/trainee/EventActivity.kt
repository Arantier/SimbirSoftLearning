package ru.shcherbakovdv.ss.trainee

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
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_charity.*
import kotlinx.android.synthetic.main.content_event_screen_images.*
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.utilites.DateUtils
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils
import java.util.regex.Pattern

class EventActivity : AppCompatActivity() {

    private lateinit var event: Charity

    private fun setupHeader(){
        toolbarTitle.text = event.title
        textTitle.text = event.title
        textEventItemDate.text = DateUtils.eventDateIntervalRepresentation(this,event.startDate,event.endDate)
        textOrganisationName.text = event.organisation.name
        textOrganisationAddress.text = event.organisation.address
        textOrganisationPhones.text = event.organisation.phones.joinToString("\n")
    }

    private fun setupOrganisationInfo(){
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
    }

    private fun ImageView.setupFriendPictureAppearance(){
        val donatorPhotoSize = resources.getDimensionPixelSize(R.dimen.donator_picture_size)
        val sizeParams = ViewGroup.LayoutParams(donatorPhotoSize, donatorPhotoSize)
        layoutParams = ViewGroup.MarginLayoutParams(sizeParams).apply {
            setMargins(
                    resources.getDimensionPixelSize(R.dimen.donator_picture_start_margin),
                    resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical),
                    0,
                    resources.getDimensionPixelSize(R.dimen.donator_picture_margin_vertical)
            )
        }
    }

    private fun TextView.setupCounterAppearance(){
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
    }

    private fun setupEventInfo(){
        ImageProvider.apply {
            loadImage(event.picturesUrlArray[0], imageBig)
            loadImage(event.picturesUrlArray[1], imageSmallTop)
            loadImage(event.picturesUrlArray[2], imageSmallBottom)
        }
        textDescription.text = event.description
    }

    private fun setupSocials(){
        val donatorsToShow = Math.min(event.donatorsPicturesUrlArray.size, 5)
        val pictureUrlArray = event.donatorsPicturesUrlArray.slice(0 until donatorsToShow)
        for (i in 0 until pictureUrlArray.size) {
            val donatorPhotoView = CircleImageView(this).apply {
                setupFriendPictureAppearance()
                elevation = i.toFloat()
            }
            ImageProvider.loadImage(pictureUrlArray[i], donatorPhotoView)
            layoutDonatorsBar.addView(donatorPhotoView)
        }
        if (event.donatorsPicturesUrlArray.size - donatorsToShow > 0) {
            val donatorCountView = TextView(this).apply {
                setupCounterAppearance()
                text = "+${event.donatorsPicturesUrlArray.size - donatorsToShow}"
            }
            layoutDonatorsBar.addView(donatorCountView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity)
        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.btn_back)
            setNavigationOnClickListener {
                finish()
            }
            inflateMenu(R.menu.category_toolbar)
        }
        event = JsonUtils.gson.fromJson(intent.getStringExtra(EVENT_JSON), Charity::class.java)
        setupHeader()
        setupOrganisationInfo()
        setupEventInfo()
        setupSocials()
    }

    companion object {
        const val EVENT_JSON = "eventjson"
    }

}
