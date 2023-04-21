package com.toddy.comunicanews.ui.activity.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.toddy.comunicanews.databinding.ActivityLoginBinding
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.webClient.FirebaseDao

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
            btnRecuperarSenha.setOnClickListener { iniciaActivity(RecuperarContaActivity::class.java) }
            btnLogin.setOnClickListener { validaDados() }
        }
    }

    private fun validaDados() {
        with(binding) {
            val email: String = edtEmail.text.toString().trim()
            val senha: String = edtSenha.text.toString().trim()

            when {
                email.isEmpty() -> {
                    edtEmail.requestFocus()
                    edtEmail.error = "Campo Obrigatório"
                }
                senha.isEmpty() -> {
                    edtSenha.requestFocus()
                    edtSenha.error = "Campo Obrigatório"
                }
                else -> {
                    progressCircular.visibility = View.VISIBLE
                    btnLogin.visibility = View.GONE

                    FirebaseDao().recuperarSenha(email, baseContext)
                }
            }
        }
    }

}