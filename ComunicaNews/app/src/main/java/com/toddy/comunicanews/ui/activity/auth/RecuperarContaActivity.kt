package com.toddy.comunicanews.ui.activity.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.toddy.comunicanews.databinding.ActivityRecuperarContaBinding
import com.toddy.comunicanews.webClient.UserDao

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
                ocultarTeclado()
                UserDao().recuperarSenha(email, baseContext)
            }
        }
    }


    private fun ocultarTeclado() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.btnLogin.windowToken, 0)
    }
}