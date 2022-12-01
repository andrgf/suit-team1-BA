package com.example.gamesuit.until

import android.content.Context
import android.media.MediaPlayer
import com.example.gamesuit.R


object BGMusic {
    private var mediaPlayer: MediaPlayer? = null

    fun createMediaplayer(context: Context) {
        mediaPlayer = MediaPlayer.create(
            context,
            R.raw.backsound
        )
    }

    fun playMusic() {
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
    }

    fun isPlay() = mediaPlayer?.isPlaying ?: false

    fun pausePlay() {
        mediaPlayer?.pause()
    }

    fun stopMusic() {
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
    }
}
