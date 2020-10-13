package com.baiana.simplemovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baiana.simplemovies.R
import com.baiana.simplemovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}