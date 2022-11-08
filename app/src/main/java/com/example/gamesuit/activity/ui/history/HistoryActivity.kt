package com.example.gamesuit.activity.ui.history

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesuit.databinding.ActivityHistoryBinding
import com.example.gamesuit.until.UserLevel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var presenterMain: PresenterHistory

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenterMain = PresenterHistory(this)

        presenterMain.getData().observe(this) {
            val recyclerView = binding.rcHistory
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = AdapterHistory(UserLevel.sortUsersLevel(it))
            binding.rcHistory.apply {
                recyclerView.adapter
            }
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}