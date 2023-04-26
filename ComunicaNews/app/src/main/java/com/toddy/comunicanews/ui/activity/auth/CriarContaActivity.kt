package com.toddy.comunicanews.ui.activity.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.toddy.comunicanews.databinding.ActivityCriarContaBinding
import com.toddy.comunicanews.models.User
import com.toddy.comunicanews.utils.isValidEmail
import com.toddy.comunicanews.webClient.FirebaseDao

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
            btnLogin.setOnClickListener { validaDados() }
        }
    }

    private fun validaDados() {
        with(binding) {
            val nome: String = edtNome.text.toString().trim()
            val email: String = edtEmail.text.toString().trim()
            val telefone: String = edtPhone.text.toString().trim()
            val senha: String = edtSenha.text.toString().trim()
            val senhaConfirmar: String = edtSenhaConfirmar.text.toString().trim()

            when {
                nome.isEmpty() -> {
                    edtNome.requestFocus()
                    edtNome.error = "Campo Obrigaório"
                }
                email.isEmpty() -> {
                    edtEmail.requestFocus()
                    edtEmail.error = "Campo Obrigaório"
                }
                !isValidEmail(email) -> {
                    edtEmail.requestFocus()
                    edtEmail.error = "Email Inválido"
                }
                telefone.isEmpty() -> {
                    edtPhone.requestFocus()
                    edtPhone.error = "Campo Obrigaório"
                }
                senha.isEmpty() -> {
                    edtSenha.requestFocus()
                    edtSenha.error = "Campo Obrigaório"
                }
                senhaConfirmar.isEmpty() -> {
                    edtSenhaConfirmar.requestFocus()
                    edtSenhaConfirmar.error = "Campo Obrigaório"
                }
                senhaConfirmar != senha -> {
                    edtSenhaConfirmar.requestFocus()
                    edtSenhaConfirmar.error = "Senhas não Batem!"
                }
                else -> {
                    ocultarTeclado()
                    btnLogin.visibility = View.GONE
                    progressCircular.visibility = View.VISIBLE

                    val user =
                        User(email = email, senha = senha, nome = nome, telefone = telefone, admin = true)

                    FirebaseDao().criarConta(this@CriarContaActivity, binding, user)
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