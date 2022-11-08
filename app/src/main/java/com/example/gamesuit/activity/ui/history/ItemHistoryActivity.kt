package com.example.gamesuit.activity.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.databinding.ActivityItemHistoryBinding

class ItemHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}