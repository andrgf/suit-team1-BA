package com.example.gamesuit.activity.ui.setting

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.activity.ui.favorite.activity.FavoriteActivity
import com.example.gamesuit.activity.ui.menu.MenuActivity
import com.example.gamesuit.activity.ui.setting.background.BackgroundActivity
import com.example.gamesuit.activity.ui.setting.language.LanguageActivity
import com.example.gamesuit.activity.ui.setting.sound.SoundActivity
import com.example.gamesuit.databinding.ActivitySettingBinding
import com.example.gamesuit.until.goto

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("SetTextI18n")
class SettingActivity : AppCompatActivity(), Settingview {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var presenterSetting: PresenterSetting

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenterSetting = PresenterSetting(this)

        binding.btnBackground.setOnClickListener {
            goto(BackgroundActivity::class.java)
        }

        binding.btnSound.setOnClickListener {
            goto(SoundActivity::class.java)
        }

        binding.btnLanguage.setOnClickListener {
            goto(LanguageActivity::class.java)
        }

        binding.btnFavorite.setOnClickListener {
            goto(FavoriteActivity::class.java)
        }

        binding.btnExit.setOnClickListener {
            goto(MenuActivity::class.java)
        }

    }

    private fun bind(user: User) {
        binding.apply {
        }
    }
}