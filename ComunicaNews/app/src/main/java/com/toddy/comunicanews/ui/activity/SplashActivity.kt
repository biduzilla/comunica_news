package com.toddy.comunicanews.ui.activity.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.toddy.comunicanews.R
import com.toddy.comunicanews.extensions.iniciaActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            iniciaActivity(LoginActivity::class.java)
            finish()
        }, 3000)
    }
}