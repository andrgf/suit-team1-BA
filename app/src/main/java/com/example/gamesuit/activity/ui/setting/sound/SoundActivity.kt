package com.example.gamesuit.activity.ui.setting.sound

import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import com.example.gamesuit.R


class SoundActivity : Activity() {
    //Mendefinisikan MediaPlayer sebagai audioBackground
    var audioBackground: MediaPlayer? = null

    /*Variabel untuk ToggleButton kita beri nama dengan myToggle*/
    var myToggle: ToggleButton? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        //Memberi definisi di onCreate untuk toggle
        myToggle = findViewById<View>(R.id.toggleSound) as ToggleButton

        //Memanggil file my_sound pada folder raw
        audioBackground = MediaPlayer.create(this, R.raw.music_loop_1)
        //Set looping ke true untuk mengulang audio jika telah selesai
        audioBackground?.setLooping(true)
        //Set volume audio agar berbunyi
        audioBackground?.setVolume(1F, 1F)
        //Memulai audio
        audioBackground?.start()
    }

    /*Mendefinisikan fungsi onToggleClicked dengan
	pengkondisian standar if/else*/
    fun onToggleClicked(view: View) {
        val on: Boolean = (view as ToggleButton).isChecked()
        if (on) {
            /*Mematikan suara audio*/
            audioBackground?.setVolume(0F, 0F)
        } else {
            /*Menghidupkan kembali audio background*/
            audioBackground?.setVolume(1F, 1F)
        }
    }

    override fun onBackPressed() {
        // TODO Auto-generated method stub
        audioBackground?.stop()
        finish()
    }
}
