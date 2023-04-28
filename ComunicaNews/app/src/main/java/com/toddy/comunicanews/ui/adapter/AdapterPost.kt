package com.toddy.comunicanews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toddy.comunicanews.databinding.ItemPostBinding
import com.toddy.comunicanews.models.Post

class AdapterPost(
    posts: List<Post> = emptyList(),
    var onClick: (post: Post) -> Unit = {}
) : RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    private val posts = posts.toMutableList()

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            itemView.setOnClickListener {
                if (::post.isInitialized) {
                    onClick(post)
                }
            }
        }

        fun vincula(post: Post) {
            this.post = post

            with(binding) {
                tvTitulo.text = post.titulo
                tvDesc.text = post.descricao
                tvQtdLikes.text = post.likes.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.vincula(post)
    }

    override fun getItemCount(): Int = posts.size

    fun atualiza(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts.reversed())
        notifyDataSetChanged()
    }
}