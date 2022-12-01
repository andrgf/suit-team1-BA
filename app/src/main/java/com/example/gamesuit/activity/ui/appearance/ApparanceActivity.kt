package com.example.gamesuit.activity.ui.appearance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.activity.ui.setting.SettingActivity
import com.example.gamesuit.databinding.ActivityApparanceBinding
import com.example.gamesuit.until.goto

class ApparanceActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityApparanceBinding
    private lateinit var viewmodel: ApparanceVm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApparanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[ApparanceVm::class.java]

        binding.switch1.isChecked = AppSharedPreference.isDarkMode == true
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewmodel.setTheme(isChecked)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switch1.isChecked = true
                Log.e("Tag", "true")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switch1.isChecked = false
                Log.e("Tag", "false")
            }
        }
        binding.btnbackhome.setOnClickListener {
            goto(SettingActivity::class.java)
        }
    }
}