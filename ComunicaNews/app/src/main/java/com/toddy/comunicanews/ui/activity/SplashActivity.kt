package com.toddy.comunicanews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.toddy.comunicanews.R
import com.toddy.comunicanews.databinding.ActivitySplashBinding
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.ui.activity.auth.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            iniciaActivity(LoginActivity::class.java)
            finish()
        }, 3000)
    }
}