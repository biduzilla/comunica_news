package com.toddy.comunicanews.webClient

import android.app.Activity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.toddy.comunicanews.models.Post

class PostDao {

    fun salvarPost(
        post: Post,
        activity: Activity,
        isNovo: Boolean,
    ) {
        val postRef = FirebaseDatabase.getInstance().reference
            .child("posts")
            .child(post.id!!)

        postRef.setValue(post)

        when {
            isNovo -> postRef.child("data").setValue(ServerValue.TIMESTAMP)
        }

        activity.finish()
    }
}