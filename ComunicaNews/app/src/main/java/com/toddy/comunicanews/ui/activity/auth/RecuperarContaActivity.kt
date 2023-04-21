package com.toddy.comunicanews.ui.activity.auth

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toddy.comunicanews.databinding.ActivityRecuperarContaBinding
import com.toddy.comunicanews.webClient.FirebaseDao

class RecuperarContaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRecuperarContaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configClick()
    }

    private fun configClick() {
        with(binding) {
            toolbarVoltar.btnVoltar.setOnClickListener { finish() }
            btnLogin.setOnClickListener { validaDados() }
        }
    }

    private fun validaDados() {
        val email: String = binding.edtEmail.text.toString().trim()

        when {
            email.isEmpty() -> {
                binding.edtEmail.requestFocus()
                binding.edtEmail.error = "Campo Obrigatório"
            }
            else -> {
                FirebaseDao().recuperarSenha(email, baseContext)
            }
        }
    }
}