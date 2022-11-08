package com.example.gamesuit.activity.ui.home

import android.content.Intent
import com.example.gamesuit.activity.ui.profil.ProfileActivity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.activity.ui.history.HistoryActivity
import com.example.gamesuit.activity.ui.leaderboard.TopscoreActivity
import com.example.gamesuit.activity.ui.menu.MenuActivity
import com.example.gamesuit.activity.ui.shop.ShopActivity
import com.example.gamesuit.databinding.ActivityHomeBinding
import com.example.gamesuit.until.goto

class HomeActivity : AppCompatActivity(), HomeContract.View  {

    lateinit var binding: ActivityHomeBinding
    private var userId: Int = 0
    private lateinit var homePresenter: HomePresenter
    private lateinit var username: TextView
    private lateinit var userimage: ImageView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = AppSharedPreference.id!!
        username = binding.tvUsername
        userimage = binding.userImg

        homePresenter = HomePresenter(this)

        username.text = userId.toString()
        homePresenter.getUser(userId).observe(this) {
            bind(it)
        }

        binding.btnPlay.setOnClickListener {
            goto(MenuActivity::class.java)
        }

        binding.btnHistory.setOnClickListener {
            goto(HistoryActivity::class.java)
        }

        binding.btnShop.setOnClickListener {
            goto(ShopActivity::class.java)
        }

        binding.btnProfil.setOnClickListener {
            goto(ProfileActivity::class.java)
        }

        binding.btnTopscore.setOnClickListener {
            goto(TopscoreActivity::class.java)
        }

        binding.btnExit.setOnClickListener {
            finishAffinity()
        }

    }

    private fun bind(user: User) {
        binding.apply {
            username.text = user.username
            userImg.setImageResource(user.avatarId)
        }
    }
}