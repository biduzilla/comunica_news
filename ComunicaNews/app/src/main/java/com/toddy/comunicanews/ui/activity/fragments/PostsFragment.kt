package com.toddy.comunicanews.ui.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toddy.comunicanews.databinding.FragmentPostsBinding
import com.toddy.comunicanews.extensions.Toast
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.preferences.dataStore
import com.toddy.comunicanews.preferences.userNomePreferences
import com.toddy.comunicanews.ui.activity.CHAVE_USER_NOME
import com.toddy.comunicanews.ui.activity.FormPostActivity
import com.toddy.comunicanews.ui.adapter.AdapterPost
import com.toddy.comunicanews.webClient.PostDao
import com.toddy.comunicanews.webClient.UserDao
import kotlinx.coroutines.launch

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        AdapterPost()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configClicks()
        configRv()

        lifecycleScope.launch {
            getUserDao()
        }
    }

    override fun onResume() {
        super.onResume()
        recuperaPosts()

        val userName = stringPreferencesKey(CHAVE_USER_NOME)

        binding.tvSaudacao.text = "Olá $userName"
    }

    private suspend fun getUserDao() {
        var name: String? = null
        UserDao.getUser(requireActivity()) {
            name = it.nome.toString()
        }

        name?.let { requireContext().dataStore.edit { pref -> pref[userNomePreferences] = it } }


    }

    private fun configRv() {
        with(binding) {
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(requireActivity())
            adapter.onClick = {
                binding.edtSearch.text.clear()
                Toast(requireContext(), it.titulo.toString())
//                Intent(requireActivity(), DetalhesPostActivity::class.java).apply {
//                    putExtra(CHAVE_POST_ID, it.id)
//                    startActivity(this)
//                }
            }
        }
    }

    private fun recuperaPosts() {
        PostDao.getAllPosts(requireActivity()) { posts ->
            if (posts.isNotEmpty()) {
                adapter.atualiza(posts)
            }
        }
    }

    private fun configClicks() {
        with(binding) {
            btnAdd.setOnClickListener { requireContext().iniciaActivity(FormPostActivity::class.java) }
        }
    }

}