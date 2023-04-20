package com.toddy.comunicanews.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toddy.comunicanews.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}