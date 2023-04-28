package com.toddy.comunicanews.webClient

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.toddy.comunicanews.models.Post

class PostDao {

    companion object {
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

        fun getAllPosts(activity: Activity, postRecuperado: (posts: List<Post>) -> Unit) {
            FirebaseAuth.getInstance().currentUser?.let {
                val posts = mutableListOf<Post>()

                FirebaseDatabase.getInstance().reference
                    .child("posts")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                snapshot.children.forEach {
                                    it.getValue(Post::class.java)?.let { post ->
                                        posts.add(post)
                                    }
                                }
                                postRecuperado(posts)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })

            } ?: UserDao.dialogLogarNovamente(activity)
        }
    }
}