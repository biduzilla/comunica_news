package com.toddy.comunicanews.ui.activity.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.toddy.comunicanews.databinding.ActivityLoginBinding
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.webClient.UserDao

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
                    ocultarTeclado()
                    progressCircular.visibility = View.VISIBLE
                    btnLogin.visibility = View.GONE

                    UserDao.login(this@LoginActivity, email = email, senha = senha)
                }
            }
        }
    }


    private fun ocultarTeclado() {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.btnLogin.windowToken, 0)
    }

}