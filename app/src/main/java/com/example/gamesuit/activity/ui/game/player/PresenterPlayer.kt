package com.binar.gamesuit.ui.playervsplayer

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.until.App
import com.example.gamesuit.until.UserLevel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class PresenterPlayer(
    private val callbackPlayerView: CallbackPlayerView,
    private val firstPlayer: String?,
    private val secondPlayer: String
) : InterfacePresenterPlayer {
    private val idUser = AppSharedPreference.id!!


    var dataUser = App.appDb.getUserDao().getUserByIdNoLiveData(idUser)
    private val point = UserLevel(dataUser)


    override fun checkSuit(
        firstPlayerChoice: String,
        secondPlayerChoice: String,
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val user = App.appDb.getUserDao()

            if (firstPlayerChoice == secondPlayerChoice) {
                Log.d("Hasil", "Draw")
                launch(Dispatchers.Main) {
                    callbackPlayerView.result("DRAW!")
                }
                point.draw()
                user.updateUser(dataUser)
            } else if (firstPlayerChoice == "Batu" && secondPlayerChoice == "Gunting" || firstPlayerChoice == "Gunting" && secondPlayerChoice == "Kertas" || firstPlayerChoice == "Kertas" && secondPlayerChoice == "Batu") {
                Log.d("Hasil", "pemain 1 win")
                launch(Dispatchers.Main) {
                    callbackPlayerView.result("$firstPlayer\nWIN!")
                }
                point.win()
                user.updateUser(dataUser)

            } else {
                Log.d("Hasil", "pemain 2/pemainDua win")
                launch(Dispatchers.Main) {
                    callbackPlayerView.result("$secondPlayer\nWIN!")
                }
                point.lose()
                user.updateUser(dataUser)
            }

            Log.d("Hasil", "$firstPlayerChoice VS $secondPlayerChoice")
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val DEFAULT_RESULT = ""
    }


}