package com.example.mercedesnews.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mercedesnews.R
import android.os.Handler
import android.os.Looper
import com.example.mercedesnews.ui.MainActivity

class SplashScreen : AppCompatActivity() {

    private val splashScreenTimer: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashScreenTimer)
    }
}