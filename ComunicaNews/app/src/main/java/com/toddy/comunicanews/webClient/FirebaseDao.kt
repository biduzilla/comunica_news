package com.toddy.comunicanews.webClient

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.toddy.comunicanews.databinding.ActivityCriarContaBinding
import com.toddy.comunicanews.extensions.Toast
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.models.User
import com.toddy.comunicanews.ui.activity.HomeActivity
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
                        activity.baseContext.iniciaActivity(HomeActivity::class.java)
                    }
                } else {
                    Toast(
                        activity.baseContext,
                        FireBaseHelper.validaErros(task.exception.toString())
                    )
                }
            }
    }

    fun salvarUser(user: User){

    }
}