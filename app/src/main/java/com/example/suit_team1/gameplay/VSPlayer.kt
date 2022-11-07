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
import com.example.suit_team1.databinding.ActivityVsplayerBinding

open class VSPlayer : AppCompatActivity(), Callback, CallbackFragment {

    private val binding by lazy { ActivityVsplayerBinding.inflate(layoutInflater) }
    val name by lazy { intent.getStringExtra("name") }
    private var resultPlayerOne = ""
    private var resultPlayerTwo = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pemain1.text = name
        showToast(this, "Selamat Bermain $name")


        val btnPlayerOne = arrayOf(
            binding.ivBatu1,
            binding.ivGunting1,
            binding.ivKertas1,
        )

        val btnPlayerTwo = arrayOf(
            binding.ivBatu2,
            binding.ivGunting2,
            binding.ivKertas2,
        )


        disableClick2(false)
        val controller = Controller(this, name, "Pemain 2")
        btnPlayerOne.forEachIndexed { index, ImageView ->
            Log.d("player 1", "click")
            ImageView.setOnClickListener {
                resultPlayerOne = btnPlayerOne[index].contentDescription.toString()

                Log.d("PLAYER 1", "Memilih $resultPlayerOne")
                showToast(this, "$name Memilih $resultPlayerOne")

                disableClick1(false)
                disableClick2(true)

                btnPlayerOne.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
            }
        }

        btnPlayerTwo.forEachIndexed { index, ImageView ->
            Log.e("player 2", "Click")
            ImageView.setOnClickListener {
                resultPlayerTwo = btnPlayerTwo[index].contentDescription.toString()
                Log.d("PLAYER 2", "Memilih $resultPlayerTwo")
                showToast(this, "Player 2 Memilih $resultPlayerTwo")
                disableClick2(false)
                if (resultPlayerTwo != "") {
                    controller.cekSuit(resultPlayerOne, resultPlayerTwo)
                    btnPlayerTwo.forEach {
                        it.setBackgroundResource(android.R.color.transparent)
                    }
                }
            }

            binding.ivRefresh.setOnClickListener {
                Log.d("reset", "Clicked")
                reset(android.R.color.transparent)
            }

            binding.ivClose.setOnClickListener {
                finish()
            }

            binding.ivHome.setOnClickListener {
                finish()
            }
        }
    }

    private fun disableClick1(isEnable: Boolean) {
        binding.ivBatu1.isEnabled = isEnable
        binding.ivKertas1.isEnabled = isEnable
        binding.ivGunting1.isEnabled = isEnable
    }

    private fun disableClick2(isEnable: Boolean) {
        binding.ivBatu2.isEnabled = isEnable
        binding.ivKertas2.isEnabled = isEnable
        binding.ivGunting2.isEnabled = isEnable
    }

    override fun result(Result: String) {
        val dialogResult = ResultFragment()
        val bundle = Bundle()
        bundle.putString("result", Result)
        dialogResult.arguments = bundle
        dialogResult.show(supportFragmentManager, "DialogResult")
    }

    override fun reset(
        backgroundklik: Int
    ) {
        binding.apply {
            ivBatu1.setBackgroundResource(backgroundklik)
            ivGunting1.setBackgroundResource(backgroundklik)
            ivKertas1.setBackgroundResource(backgroundklik)
            ivBatu2.setBackgroundResource(backgroundklik)
            ivGunting2.setBackgroundResource(backgroundklik)
            ivKertas2.setBackgroundResource(backgroundklik)
        }
        resultPlayerOne = ""
        resultPlayerTwo = ""
        disableClick1(true)
        disableClick2(false)

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}