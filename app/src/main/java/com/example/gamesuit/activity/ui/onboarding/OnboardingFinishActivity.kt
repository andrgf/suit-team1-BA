package com.example.gamesuit.activity.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.activity.ui.login.LoginActivity
import com.example.gamesuit.activity.ui.registrasi.RegistrasiActivity
import com.example.gamesuit.databinding.ActivityOnboardingFinishBinding
import com.example.gamesuit.until.goto

class OnboardingFinishActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardingFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            goto(LoginActivity::class.java)
        }

        binding.btnRegistrasi.setOnClickListener {
            goto(RegistrasiActivity::class.java)
        }
    }
}