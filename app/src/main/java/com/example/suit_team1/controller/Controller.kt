package com.example.suit_team1.controller

import android.util.Log
import com.example.suit_team1.callback.Callback

class Controller(
    private val callback: Callback,
    private val pilihanpertama: String?,
    private val pilihankedua: String
) : CallbackController {

    override fun cekSuit(pertama: String, kedua: String) {
        when {
            pertama == kedua -> {
                Log.d("Hasil", "Draw")
                Log.e("Hasil", "Draw")
                callback.result("Seri!")

            }
            pertama == "Batu" && kedua == "Gunting" || pertama == "Gunting" && kedua == "Kertas" || pertama == "Kertas" && kedua == "Batu" -> {
                Log.d("Hasil", "pemain 1 win")
                Log.e("Hasil", "pemain 1 win")
                callback.result("$pilihanpertama \n  Menang!")

            }
            else -> {
                Log.d("Hasil", "pemain 2/pemainDua win")
                Log.e("Hasil", "pemain 2/pemainDua win")
                callback.result("$pilihankedua \n Menang!")
            }
        }
        Log.d("Hasil", "$pertama VS $kedua")
        Log.e("Hasil", "$pertama VS $kedua")
    }

}