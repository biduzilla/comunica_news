package com.toddy.comunicanews.webClient

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.toddy.comunicanews.databinding.ActivityCriarContaBinding
import com.toddy.comunicanews.databinding.ActivityLoginBinding
import com.toddy.comunicanews.databinding.DialogUserLogarBinding
import com.toddy.comunicanews.extensions.Toast
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.models.User
import com.toddy.comunicanews.ui.activity.HomeActivity
import com.toddy.comunicanews.ui.activity.auth.LoginActivity
import com.toddy.comunicanews.utils.FireBaseHelper

class UserDao {

    companion object {
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
                            userCriado.id = idUser
                            salvarUser(userCriado, activity)
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

        fun salvarUser(user: User, activity: Activity? = null) {
            FirebaseDatabase.getInstance().reference
                .child("users")
                .child("admin")
                .child(user.id!!)
                .setValue(user)

            activity?.iniciaActivity(LoginActivity::class.java)
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

        fun getIdUser(activity: Activity): String? {
            FirebaseAuth.getInstance().currentUser?.let {
                return it.uid
            }
            android.widget.Toast.makeText(
                activity.baseContext,
                "Usuario não logado na conta",
                android.widget.Toast.LENGTH_SHORT
            )
                .show()
            dialogLogarNovamente(activity)
            return null
        }

        fun dialogLogarNovamente(activity: Activity) {
            DialogUserLogarBinding.inflate(activity.layoutInflater).apply {
                val dialog = AlertDialog.Builder(activity)
                    .setView(root)
                    .create()

                dialog.setCancelable(false)
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()

                btnLogin.setOnClickListener {
                    FirebaseAuth.getInstance().signOut()
                    activity.iniciaActivity(LoginActivity::class.java)
                    activity.finish()
                }
            }
        }

        fun getUser(activity: Activity, userRecuperado: (userRecuperado: User) -> Unit) {
            getIdUser(activity)?.let { idUser ->
                FirebaseDatabase.getInstance().reference
                    .child("users")
                    .child("admin")
                    .child(idUser)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                snapshot.getValue(User::class.java)?.let {
                                    userRecuperado(it)

                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
            }
        }
    }
}