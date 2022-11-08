package com.example.gamesuit.activity.ui.menu

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.game.cpu.GameCpuActivity
import com.example.gamesuit.activity.ui.game.player.GamePlayerActivity
import com.example.gamesuit.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("SetTextI18n")
class MenuActivity : AppCompatActivity(), MenuView {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var presenterMenu: PresenterMenu

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenterMenu = PresenterMenu(this)

        val username = presenterMenu.getDataUser().username

        binding.apply {
            tvPemainVsPemain.text = getString(R.string.pemain_vs_pemain, username)
            tvPemainVsCPU.text = getString(R.string.pemain_vs_cpu, username)
        }


        binding.ivPemainVsPemain.setOnClickListener {
            val mIntent = Intent(this, GamePlayerActivity::class.java)
             startActivity(mIntent)

        }
        binding.ivPemainVsCPU.setOnClickListener {
            val mIntent = Intent(this, GameCpuActivity::class.java)
            startActivity(mIntent)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        Snackbar.make(
            binding.menuActivity,
            "Selamat datang $username",
            Snackbar.LENGTH_LONG
        ).setAction("TUTUP") {
        }.show()
    }
}