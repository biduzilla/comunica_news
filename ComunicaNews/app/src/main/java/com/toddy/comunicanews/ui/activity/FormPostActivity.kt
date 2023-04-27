package com.toddy.comunicanews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.toddy.comunicanews.databinding.ActivityFormPostBinding
import com.toddy.comunicanews.models.Post
import com.toddy.comunicanews.webClient.UserDao
import java.util.Calendar

class FormPostActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormPostBinding.inflate(layoutInflater)
    }
    private var post: Post? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun validaDados(){
        with(binding){
            val titulo:String =edtTitulo.text.toString().trim()
            val desc:String =edtDesc.text.toString().trim()

            when{
                titulo.isEmpty() -> {
                    edtTitulo.requestFocus()
                    edtTitulo.error = "Campo Obrigatório"
                }
                desc.isEmpty() -> {
                    edtDesc.requestFocus()
                    edtDesc.error = "Campo Obrigatório"
                }
                else->{

                    if (post == null) post = Post()
                    salvarPost(post!!)
                }
            }
        }

    }

    private fun salvarPost(post: Post) {
        post.id = UserDao().getIdUser(this@FormPostActivity)
        UserDao().getUser(this@FormPostActivity){
            post.autor = it.nome
        }



    }
}