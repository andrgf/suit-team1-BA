package com.example.gamesuit.activity.ui.registrasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.activity.ui.home.HomeActivity
import com.example.gamesuit.databinding.ActivityRegisterConfirmationBinding

class RegisterConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterConfirmationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}