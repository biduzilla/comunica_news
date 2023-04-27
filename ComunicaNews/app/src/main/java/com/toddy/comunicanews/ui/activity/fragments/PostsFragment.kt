package com.toddy.comunicanews.ui.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toddy.comunicanews.R
import com.toddy.comunicanews.databinding.FragmentPostsBinding
import com.toddy.comunicanews.extensions.iniciaActivity
import com.toddy.comunicanews.ui.activity.FormPostActivity

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configClicks()
    }

    private fun configClicks() {
        with(binding) {
            btnAdd.setOnClickListener { requireActivity().iniciaActivity(FormPostActivity::class.java) }
        }
    }

}