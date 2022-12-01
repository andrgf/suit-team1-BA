package com.example.gamesuit.activity.ui.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.gamesuit.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private lateinit var adapter: VideoAdpater
    private lateinit var binding: ActivityVideoBinding
    private val videos = ArrayList<VideoData>()
    private val exoPlayerItems = ArrayList<ExoPlayerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItem()
        adapter =
            VideoAdpater(this, videos, object : VideoAdpater.OnVideoPreparedListener {
                override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                    exoPlayerItems.add(exoPlayerItem)
                }
            })

        binding.viewPager2.adapter = adapter
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                if (previousIndex != -1) {
                    val player = exoPlayerItems[previousIndex].exoPlayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1) {
                    val player = exoPlayerItems[newIndex].exoPlayer
                    player.playWhenReady = true
                    player.play()
                }
            }
        })

    }

    override fun onPause() {
        super.onPause()

        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.pause()
            player.playWhenReady = false
        }
    }

    override fun onResume() {
        super.onResume()

        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.playWhenReady = true
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (exoPlayerItems.isNotEmpty()) {
            for (item in exoPlayerItems) {
                val player = item.exoPlayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }

    fun addItem() {
        videos.add(
            VideoData(
                "Big Buck Bunny",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        )

        videos.add(
            VideoData(
                "Elephant Dream",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Blazes",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Escape",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Fun",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Blazes",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Blazes",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Joyrides",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
            )
        )

        videos.add(
            VideoData(
                "For Bigger Meltdowns",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"
            )
        )

        videos.add(
            VideoData(
                "Sintel",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"
            )
        )

        videos.add(
            VideoData(
                "Subaru Outback On Street And Dirt",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"
            )
        )

        videos.add(
            VideoData(
                "Tears of Steel",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
            )
        )

        videos.add(
            VideoData(
                "Volkswagen GTI Review",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
            )
        )

        videos.add(
            VideoData(
                "We Are Going On Bullrun",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
            )
        )

        videos.add(
            VideoData(
                "What care can you get for a grand?",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
            )
        )
    }
}
