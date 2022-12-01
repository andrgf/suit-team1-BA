package com.example.gamesuit.activity.ui.sound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.gamesuit.R
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.activity.ui.home.HomeActivity
import com.example.gamesuit.activity.ui.setting.SettingActivity
import com.example.gamesuit.databinding.ActivitySoundBinding
import com.example.gamesuit.until.BGMusic.isPlay
import com.example.gamesuit.until.BGMusic.playMusic
import com.example.gamesuit.until.BGMusic.stopMusic
import com.example.gamesuit.until.goto

class SoundActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySoundBinding
    private lateinit var viewmodel: SoundVm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[SoundVm::class.java]

        binding.switch1.isChecked = AppSharedPreference.isMusicPlay
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            viewmodel.setMusic(isChecked)
            if (isChecked) {
                playMusic()
                Toast.makeText(
                    this@SoundActivity, "Music ON", Toast.LENGTH_SHORT).show()
                binding.switch1.isChecked = true
            } else if (!isChecked && isPlay()) {
                stopMusic()
                binding.switch1.isChecked = false
            }
        }

        binding.btnbackhome.setOnClickListener {
            goto(SettingActivity::class.java)
        }

    }
}