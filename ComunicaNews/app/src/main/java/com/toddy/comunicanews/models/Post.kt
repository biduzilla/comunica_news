package com.toddy.comunicanews.models

data class Post(
    var id: String? = "",
    var titulo: String? = "",
    var descricao: String? = "",
    var autor: String? = "",
    var data: String? = "",
    var likes: Int? = 0,
    var comentarios: List<Comentario> = listOf()
)