package com.toddy.comunicanews.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.Exclude
import com.google.firebase.ktx.Firebase

data class User(
    var id: String? = null,
    var email: String? = null,
    @get:Exclude
    var senha: String? = null,
    var nome: String? = null,
    var telefone: String? = null,
    var admin: Boolean = false,
)
