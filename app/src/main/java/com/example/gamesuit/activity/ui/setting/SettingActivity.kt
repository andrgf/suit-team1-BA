package com.example.gamesuit.activity.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesuit.activity.ui.appearance.ApparanceActivity
import com.example.gamesuit.activity.ui.home.HomeActivity
import com.example.gamesuit.activity.ui.profil.ProfileActivity
import com.example.gamesuit.databinding.ActivitySettingBinding
import com.example.gamesuit.until.goto

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfil.setOnClickListener {
            goto(ProfileActivity::class.java)
        }

        binding.btnAppearance.setOnClickListener {
            goto(ApparanceActivity::class.java)
        }

        binding.btnbackhome.setOnClickListener {
            goto(HomeActivity::class.java)
        }
    }
}