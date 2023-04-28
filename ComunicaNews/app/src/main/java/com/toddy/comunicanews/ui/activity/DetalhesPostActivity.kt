package com.toddy.comunicanews.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toddy.comunicanews.databinding.ActivityDetalhesPostBinding

class DetalhesPostActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesPostBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}