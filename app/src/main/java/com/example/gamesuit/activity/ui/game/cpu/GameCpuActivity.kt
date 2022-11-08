package com.example.gamesuit.activity.ui.game.cpu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityGameCpuBinding
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.activity.ui.game.CallbackDialogHasilFragment
import com.example.gamesuit.activity.ui.game.CallbackDialogHasil
import com.example.gamesuit.until.App
import com.example.gamesuit.until.UserLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
class GameCpuActivity : AppCompatActivity(), CpuView,CallbackDialogHasil {

    private lateinit var binding: ActivityGameCpuBinding
    private lateinit var player1: User
    private lateinit var presenter: PresenterCpu
    private var isPlayerTurn = true

    private lateinit var p1Choices: List<ImageView>
    private lateinit var comChoices: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameCpuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player1 = App.appDb.getUserDao().getUserByIdNoLiveData(AppSharedPreference.id!!)
        presenter = PresenterCpu(this, player1.username)
        binding.pemain1.text = player1.username

        binding.ivBack.setOnClickListener { onBackPressed() }

        binding.ivRefresh.setOnClickListener {
            if (isPlayerTurn) {
                reset(Color.TRANSPARENT)
            }
        }

        p1Choices = listOf(
            binding.ivBatu,
            binding.ivKertas,
            binding.ivGunting
        )

        comChoices = listOf(
            binding.ivBatucom,
            binding.ivKertascom,
            binding.ivGuntingcom
        )

        p1Choices.forEach { its ->
            its.setOnClickListener {
                if (isPlayerTurn) {
                    it.setBackgroundResource(R.drawable.backgorund_shape)
                    showToast("${player1.username} Memilih ${it.contentDescription}")
                    presenter.comTurn(it.contentDescription.toString())
                    isPlayerTurn = false
                }
            }
        }

    }

    override fun hasil(hasil: String, status: Int) {
        val userLevel = UserLevel(player1)

        when (status) {
            0 -> userLevel.draw()
            1 -> userLevel.win()
            -1 -> userLevel.lose()
        }

        GlobalScope.launch(Dispatchers.Default) {
            App.appDb.getUserDao().updateUser(player1)
        }
    }

    override fun createDialog(resultString: String, result: Int) {
        val dialogResult = CallbackDialogHasilFragment()
        val bundle = Bundle()
        when (result) {
            0 -> {
                bundle.putString(CallbackDialogHasilFragment.RESULT, "DRAW!")
            }
            1 -> {
                bundle.putString(CallbackDialogHasilFragment.RESULT, "$resultString\nWIN!")
            }
            -1 -> {
                bundle.putString(CallbackDialogHasilFragment.RESULT, "COM\nWIN!")
            }
        }
        dialogResult.arguments = bundle
        dialogResult.show(supportFragmentManager, CallbackDialogHasilFragment.TAG)
    }

    override fun reset(bgReset: Int) {
        p1Choices.forEach {
            it.setBackgroundColor(Color.TRANSPARENT)
        }
        comChoices.forEach {
            it.setBackgroundColor(Color.TRANSPARENT)
        }
        isPlayerTurn = true
    }

    override suspend fun comPlay(id: Int) {
        delay(800)
        comChoices.forEach { image ->
            GlobalScope.launch(Dispatchers.Main) {
                image.setBackgroundResource(R.drawable.backgorund_shape)
            }
            delay(400)
            GlobalScope.launch(Dispatchers.Main) {
                image.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        delay(800)
        GlobalScope.launch(Dispatchers.Main) {
            comChoices[id].setBackgroundResource(R.drawable.backgorund_shape)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}