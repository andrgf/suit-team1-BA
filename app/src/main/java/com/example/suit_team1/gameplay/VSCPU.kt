package com.example.suit_team1.gameplay

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.suit_team1.R
import com.example.suit_team1.callback.Callback
import com.example.suit_team1.callback.CallbackFragment
import com.example.suit_team1.callback.ResultFragment
import com.example.suit_team1.controller.Controller
import com.example.suit_team1.databinding.ActivityVscpuBinding

open class VSCPU : AppCompatActivity(), Callback, CallbackFragment {

    private val binding by lazy { ActivityVscpuBinding.inflate(layoutInflater) }
    val name by lazy { intent.getStringExtra("name") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pemain.text = name


        val btnPlayer = arrayOf(
            binding.ivBatu,
            binding.ivGunting,
            binding.ivKertas,
        )

        val btnCom = arrayOf(
            binding.ivBatucom,
            binding.ivKertascom,
            binding.ivGuntingcom,
        )


        val controller = Controller(this, name, "CPU")
        btnPlayer.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                val hasilCom = btnCom.random()
                val hasilPemain = btnPlayer[index].contentDescription.toString()

                Log.d("PEMAIN SATU", "Memilih $hasilPemain")
                Log.e("PEMAIN SATU", "Memilih $hasilPemain")
                showToast(this, "$name Memilih $hasilPemain")
                disableClick(false)


                controller.cekSuit(
                    hasilPemain, hasilCom.contentDescription.toString()
                )
                Log.d("CPU", "Memilih $hasilCom")
                showToast(this, "CPU Memilih ${hasilCom.contentDescription}")
                btnPlayer.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
            }
        }

        binding.ivRefresh.setOnClickListener {
            Log.d("reset", "Clicked")
            reset(android.R.color.transparent)
        }

        binding.ivKeluar.setOnClickListener {
            finish()

        }

        binding.ivHome.setOnClickListener {
            finish()
        }
    }


    private fun disableClick(isEnable: Boolean) {
        binding.apply {
            ivBatu.isEnabled = isEnable
            ivKertas.isEnabled = isEnable
            ivGunting.isEnabled = isEnable
        }
    }


    override fun result(result: String) {
        val dialogResult = ResultFragment()
        val bundle = Bundle()
        bundle.putString("result", result)
        dialogResult.arguments = bundle
        dialogResult.show(supportFragmentManager, "DialogResult")
    }

    override fun reset(
        backgroundklik: Int
    ) {
        binding.apply {
            ivBatu.setBackgroundResource(backgroundklik)
            ivKertas.setBackgroundResource(backgroundklik)
            ivGunting.setBackgroundResource(backgroundklik)
            ivBatucom.setBackgroundResource(backgroundklik)
            ivKertascom.setBackgroundResource(backgroundklik)
            ivGuntingcom.setBackgroundResource(backgroundklik)
        }
        disableClick(true)

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}