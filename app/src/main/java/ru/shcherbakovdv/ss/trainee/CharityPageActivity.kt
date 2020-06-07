package ru.shcherbakovdv.ss.trainee

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_charity.*
import kotlinx.android.synthetic.main.content_charity_screen_images.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.shcherbakovdv.ss.trainee.data.Charity
import ru.shcherbakovdv.ss.trainee.data.Organisation
import ru.shcherbakovdv.ss.trainee.data.providers.ImageProvider
import ru.shcherbakovdv.ss.trainee.utilites.DateUtils
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeGone
import ru.shcherbakovdv.ss.trainee.utilites.extensions.makeVisible
import ru.shcherbakovdv.ss.trainee.utilites.json.JsonUtils
import java.util.regex.Pattern

// TODO: Ну и мерзкий экран. Переделать.
class CharityPageActivity : MvpAppCompatActivity(), CharityPageMvpView {

    private val presenter by moxyPresenter { CharityPagePresenter() }

    override fun setLoadingScreen() {
        bottomBar.makeGone()
        content.makeGone()
        error.makeGone()
        progressBar.makeVisible()
    }

    override fun setCharityScreen(charity: Charity, organisation: Organisation) {
        progressBar.makeGone()
        error.makeGone()
        bottomBar.makeVisible()
        content.makeVisible()

        toolbarTitle.text = charity.title
        textTitle.text = charity.title
        textEventItemDate.text =
            DateUtils.eventDateIntervalRepresentation(this, charity.startDate, charity.endDate)

        textOrganisationName.text = organisation.name
        textOrganisationAddress.text = organisation.address
        textOrganisationPhones.text = organisation.phones.joinToString("\n")
        textMessage.apply {
            text = getString(R.string.charity_screen_organisation_message)
            val p = Pattern.compile("Напишите нам!")
            Linkify.addLinks(this, p, organisation.email)
        }
        textHyperlink.apply {
            val siteString = getString(R.string.charity_screen_organisation_site)
            val siteUrlString = SpannableString(siteString).apply {
                setSpan(
                    URLSpan(organisation.site),
                    0,
                    siteString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            text = siteUrlString
            movementMethod = LinkMovementMethod.getInstance()
        }

        ImageProvider.apply {
            loadImage(charity.picturesUrls[0], imageBig)
            loadImage(charity.picturesUrls[1], imageSmallTop)
            loadImage(charity.picturesUrls[2], imageSmallBottom)
        }
        textDescription.text = charity.description

        val donatorsToShow = Math.min(charity.donatorsPicturesUrls?.size ?: 0, 5)
        val pictureUrlArray = charity.donatorsPicturesUrls?.slice(0 until donatorsToShow) ?: null
        if (!pictureUrlArray.isNullOrEmpty()) {
            for (i in 0 until (pictureUrlArray?.size ?: 0)) {
                val donatorPhotoView = CircleImageView(this).apply {
                    val donatorPhotoSize =
                        resources.getDimensionPixelSize(R.dimen.donator_picture_size)
                    val sizeParams = ViewGroup.LayoutParams(donatorPhotoSize, donatorPhotoSize)
                    layoutParams = ViewGroup.MarginLayoutParams(sizeParams).apply {
                        setMargins(
                            resources.getDimensionPixelSize(R.dimen.donator_picture_start_margin),
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
        }
        if ((charity.donatorsPicturesUrls?.size ?: 0) - donatorsToShow > 0) {
            val donatorCountView = TextView(this).apply {
                val sizeParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
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
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.event_screen_donators_text_size)
                )
                text = "+${(charity.donatorsPicturesUrls?.size ?: 0) - donatorsToShow}"
            }
            layoutDonatorsBar.addView(donatorCountView)
        }
    }

    override fun setErrorScreen(t: Throwable) {
        progressBar.makeGone()
        content.makeGone()
        bottomBar.makeGone()
        error.makeVisible()
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
        presenter.prepareScreen(
            JsonUtils.gson.fromJson(
                intent.getStringExtra(EVENT_JSON),
                Charity::class.java
            )
        )
    }

    companion object {
        const val EVENT_JSON = "eventjson"
    }

}
