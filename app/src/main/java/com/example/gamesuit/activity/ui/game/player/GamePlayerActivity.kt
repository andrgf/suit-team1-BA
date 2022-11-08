package com.example.gamesuit.activity.ui.game.player

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.binar.gamesuit.ui.playervsplayer.CallbackPlayerView
import com.binar.gamesuit.ui.playervsplayer.PresenterPlayer
import com.binar.gamesuit.ui.playervsplayer.PresenterPlayer.Companion.DEFAULT_RESULT
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.game.CallbackDialogHasil
import com.example.gamesuit.activity.ui.game.CallbackDialogHasilFragment
import com.example.gamesuit.activity.ui.game.CallbackDialogHasilFragment.Companion.RESULT
import com.example.gamesuit.activity.ui.game.CallbackDialogHasilFragment.Companion.TAG
import com.example.gamesuit.databinding.ActivityGamePlayerBinding

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
class GamePlayerActivity : AppCompatActivity(), CallbackPlayerView, CallbackDialogHasil {

    private val binding by lazy { ActivityGamePlayerBinding.inflate(layoutInflater) }
    private var presenter = PresenterPlayer(this, DEFAULT_RESULT, DEFAULT_RESULT)
    private lateinit var firstPlayerChoice: ImageView
    private lateinit var secondPlayerChoice: ImageView
    private var username = presenter.dataUser.username
    private var firstPlayerResult = DEFAULT_RESULT
    private var secondPlayerResult = DEFAULT_RESULT
    private val secondPlayer = "Player 2"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = PresenterPlayer(this, username, secondPlayer)


        binding.pemain1.text = username
        presenter.showToast(this, getString(R.string.choose_first, username))


        val btnPemainSatu = arrayOf(
            binding.ivBatu1,
            binding.ivKertas1,
            binding.ivGunting1,
        )

        val btnPemainDua = arrayOf(
            binding.ivBatu2,
            binding.ivKertas2,
            binding.ivGunting2,
        )


        disableClick2(false)
        btnPemainSatu.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                firstPlayerResult = btnPemainSatu[index].contentDescription.toString()
                firstPlayerChoice = btnPemainSatu[index]
                presenter.showToast(
                    this,
                    getString(R.string.player_choose, username, firstPlayerResult)
                )

                disableClick1(false)
                disableClick2(true)

                btnPemainSatu.forEach {
                    it.setBackgroundResource(R.drawable.backgorund_shape)
                }
            }
        }
        btnPemainDua.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                secondPlayerResult = btnPemainDua[index].contentDescription.toString()
                secondPlayerChoice = btnPemainDua[index]
                presenter.showToast(
                    this,
                    getString(R.string.player_choose, secondPlayer, secondPlayerResult)
                )
                disableClick2(false)
                btnPemainDua.forEach {
                    it.setBackgroundResource(R.drawable.backgorund_shape)
                }
            }
        }

        binding.btnShow.setOnClickListener {
            if (firstPlayerResult != DEFAULT_RESULT && secondPlayerResult != DEFAULT_RESULT) {
                presenter.checkSuit(firstPlayerResult, secondPlayerResult)
                reset(android.R.color.transparent)
                firstPlayerChoice.setBackgroundResource(R.drawable.backgorund_shape)
                secondPlayerChoice.setBackgroundResource(R.drawable.backgorund_shape)

            } else if (firstPlayerResult != DEFAULT_RESULT && secondPlayerResult == DEFAULT_RESULT) {
                presenter.showToast(this, getString(R.string.havent_choose, secondPlayer))
            } else {
                presenter.showToast(this, getString(R.string.havent_choose, username))

            }
        }

        binding.ivRefresh.setOnClickListener {
            reset(android.R.color.transparent)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    override fun disableClick1(isEnable: Boolean) {
        binding.ivKertas1.isEnabled = isEnable
        binding.ivBatu1.isEnabled = isEnable
        binding.ivGunting1.isEnabled = isEnable
    }

    override fun disableClick2(isEnable: Boolean) {
        binding.ivKertas2.isEnabled = isEnable
        binding.ivBatu2.isEnabled = isEnable
        binding.ivGunting2.isEnabled = isEnable
    }

    override fun result(result: String) {
        val dialogResult = CallbackDialogHasilFragment()
        val bundle = Bundle()
        bundle.putString(RESULT, result)
        dialogResult.arguments = bundle
        dialogResult.show(supportFragmentManager, TAG)
    }

    override fun reset(
        bgReset: Int
    ) {
        binding.apply {
            ivBatu1.setBackgroundResource(bgReset)
            ivKertas1.setBackgroundResource(bgReset)
            ivGunting1.setBackgroundResource(bgReset)
            ivBatu2.setBackgroundResource(bgReset)
            ivKertas2.setBackgroundResource(bgReset)
            ivGunting2.setBackgroundResource(bgReset)
        }
        firstPlayerResult = DEFAULT_RESULT
        secondPlayerResult = DEFAULT_RESULT
        disableClick1(true)
        disableClick2(false)

    }
}