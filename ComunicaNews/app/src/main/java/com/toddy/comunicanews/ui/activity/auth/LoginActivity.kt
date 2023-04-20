package com.toddy.comunicanews.ui.activity.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toddy.comunicanews.databinding.ActivityLoginBinding
import com.toddy.comunicanews.extensions.iniciaActivity

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configClicks()
    }

    private fun configClicks() {
        with(binding) {
            btnCriarConta.setOnClickListener { iniciaActivity(CriarContaActivity::class.java) }
        }
    }
}