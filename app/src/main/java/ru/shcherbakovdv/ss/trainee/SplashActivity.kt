package ru.shcherbakovdv.ss.trainee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.content.Intent
import android.os.Handler
import ru.shcherbakovdv.ss.trainee.mainscreen.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar = progressLoading

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
        },0)
    }
}
