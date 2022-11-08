package com.example.gamesuit.activity.ui.leaderboard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.gamesuit.ui.leaderboard.MainView
import com.binar.gamesuit.ui.leaderboard.PresenterTopscore
import com.example.gamesuit.databinding.ActivityTopscoreBinding
import com.example.gamesuit.until.UserLevel

class TopscoreActivity : AppCompatActivity(),MainView {
    private lateinit var binding: ActivityTopscoreBinding
    private lateinit var presenterMain: PresenterTopscore

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopscoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenterMain = PresenterTopscore(this)

        presenterMain.getData().observe(this) {
            val recyclerView = binding.rcTopscore
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = AdapterPlayer(UserLevel.sortUsersLevel(it))
            binding.rcTopscore.apply {
                recyclerView.adapter
            }
        }
        binding.btnbackhome.setOnClickListener {
            onBackPressed()
        }
    }
}