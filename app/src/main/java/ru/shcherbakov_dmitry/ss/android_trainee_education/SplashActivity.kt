package ru.shcherbakov_dmitry.ss.android_trainee_education

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_splash.*
import android.content.Intent
import android.os.Handler


class SplashActivity : AppCompatActivity() {

    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar = progress_loading

        //TODO:Поменяй 0 на 2000
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HelpCategoriesActivity::class.java))
        },2000)
    }
}
