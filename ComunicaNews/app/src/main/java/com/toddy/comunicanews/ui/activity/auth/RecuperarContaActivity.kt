package com.toddy.comunicanews.ui.activity.auth

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toddy.comunicanews.databinding.ActivityRecuperarContaBinding

class RecuperarContaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRecuperarContaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}