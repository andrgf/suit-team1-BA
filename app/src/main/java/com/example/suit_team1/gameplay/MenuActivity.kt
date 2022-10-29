package com.example.suit_team1.gameplay

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suit_team1.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")

        binding.tvPemainVsPemain.text = "$name VS Player"
        binding.tvPemainVsCPU.text = "$name VS CPU"

        binding.ivPemainVsPemain.setOnClickListener {
            val mIntent = Intent(this, VSPlayerActivity::class.java)
            mIntent.putExtra("name", name)
            startActivity(mIntent)

        }
        binding.ivPemainVsCPU.setOnClickListener {
            val mIntent = Intent(this, VSCPU::class.java)
            mIntent.putExtra("name", name)
            startActivity(mIntent)
        }

        Snackbar.make(
            binding.menuActivity,
            "Selamat datang $name",
            Snackbar.LENGTH_LONG
        ).setAction("TUTUP") {
        }.show()
    }
}