package com.toddy.comunicanews.webClient

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.toddy.comunicanews.databinding.ActivityCriarContaBinding
import com.toddy.comunicanews.databinding.ActivityLoginBinding
import com.toddy.comunicanews.extensions.Toast
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.models.User
import com.toddy.comunicanews.ui.activity.HomeActivity
import com.toddy.comunicanews.ui.activity.auth.LoginActivity
import com.toddy.comunicanews.utils.FireBaseHelper

class FirebaseDao {

    fun criarConta(
        activity: Activity,
        binding: ActivityCriarContaBinding,
        userCriado: User
    ) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(userCriado.email!!, userCriado.senha!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.user?.let { FirebaseUser ->
                        val idUser = FirebaseUser.uid
                        salvarUser(userCriado)

                        activity.baseContext.iniciaActivity(LoginActivity::class.java)
                    }
                } else {
                    binding.progressCircular.visibility = View.GONE
                    binding.btnLogin.visibility = View.VISIBLE
                    Toast(
                        activity.baseContext,
                        FireBaseHelper.validaErros(task.exception.toString())
                    )
                }
            }
    }

    fun salvarUser(user: User) {
        FirebaseDatabase.getInstance().reference
            .child("users")
            .child("admin")
            .child(user.id!!)
            .setValue(user)
    }

    fun recuperarSenha(email: String, context: Context) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast(context, "Email enviado para recuperar sua senha")
            } else {
                Toast(context, it.exception.toString())
            }
        }
    }

    fun login(
        activity: Activity,
        email: String,
        senha: String,
        binding: ActivityLoginBinding? = null
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    activity.iniciaActivity(HomeActivity::class.java)
                } else {
                    binding?.let {
                        it.progressCircular.visibility = View.GONE
                        it.btnLogin.visibility = View.VISIBLE
                    }
                    android.widget.Toast.makeText(
                        activity.baseContext,
                        FireBaseHelper.validaErros(task.exception.toString()),
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}