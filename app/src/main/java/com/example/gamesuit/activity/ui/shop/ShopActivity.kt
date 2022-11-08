package com.example.gamesuit.activity.ui.shop

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity(),ShopView {
    private lateinit var binding: ActivityShopBinding
    private lateinit var presenterShop: PresenterShop
    private var userId: Int = 0
    private lateinit var username: TextView
    private lateinit var userimage: ImageView
    private lateinit var point : TextView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenterShop = PresenterShop(this)
        userId = AppSharedPreference.id!!
        username = binding.tvUsername
        userimage = binding.userImg
        point = binding.tvAngkapoint



        username.text = userId.toString()
        presenterShop.getUser(userId).observe(this) {
            bind(it)
        }


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bind(user: User) {
        binding.apply {
            username.text = user.username
            userImg.setImageResource(user.avatarId)
            point.text = user.point.toString()
        }
    }
}