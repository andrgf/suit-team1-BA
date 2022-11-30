package com.example.gamesuit.activity.ui.setting.language

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.databinding.ActivityLanguageBinding
import java.util.*

class LanguageActivity : AppCompatActivity() {

    private val localeList =  listOf("ja", "ko", "fr", "jv")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLanguageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.spLanguagePicker.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    println("Selected locale = ${localeList[p2]}")
                    setAppLocale(Locale.forLanguageTag(localeList[p2]))

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setAppLocale(locale: Locale) {
        val localeManager = getSystemService(LocaleManager::class.java)
        localeManager.applicationLocales = LocaleList(locale)
    }

}