package com.toddy.comunicanews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.toddy.comunicanews.databinding.ActivityFormPostBinding
import com.toddy.comunicanews.models.Post
import com.toddy.comunicanews.webClient.PostDao
import com.toddy.comunicanews.webClient.UserDao
import java.util.Calendar

class FormPostActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormPostBinding.inflate(layoutInflater)
    }
    private var post: Post? = null
    private var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUpdate) {
            binding.toolbarVoltar.tvTitulo.text = "Cadastrar Post"
        } else {
            binding.toolbarVoltar.tvTitulo.text = "Atualizar Post"
        }

        setContentView(binding.root)
    }

    private fun validaDados() {
        with(binding) {
            val titulo: String = edtTitulo.text.toString().trim()
            val desc: String = edtDesc.text.toString().trim()

            when {
                titulo.isEmpty() -> {
                    edtTitulo.requestFocus()
                    edtTitulo.error = "Campo Obrigatório"
                }
                desc.isEmpty() -> {
                    edtDesc.requestFocus()
                    edtDesc.error = "Campo Obrigatório"
                }
                else -> {
                    progressCircular.visibility = View.VISIBLE
                    btnLogin.visibility = View.GONE

                    if (post == null) post = Post()
                    salvarPost(post!!)
                }
            }
        }
    }

    private fun salvarPost(post: Post) {
        UserDao.getUser(this@FormPostActivity) {
            post.autor = it.nome
        }
        post.id = FirebaseDatabase.getInstance().reference.push().key

        PostDao.salvarPost(post, this@FormPostActivity,!isUpdate)

    }
}