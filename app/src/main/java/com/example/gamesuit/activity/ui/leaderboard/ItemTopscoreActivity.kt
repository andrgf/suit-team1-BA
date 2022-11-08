package com.example.gamesuit.activity.ui.leaderboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityItemTopscoreBinding
import com.example.gamesuit.databinding.ActivityTopscoreBinding

class ItemTopscoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemTopscoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemTopscoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}