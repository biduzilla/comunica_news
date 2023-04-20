package com.toddy.comunicanews.ui.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toddy.comunicanews.databinding.ActivityCriarContaBinding

class CriarContaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCriarContaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configClicks()
    }

    private fun configClicks() {
        with(binding) {
            toolbarVoltar.tvTitulo.text = "Criar Conta"
            toolbarVoltar.btnVoltar.setOnClickListener { finish() }
        }
    }
}