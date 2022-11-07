package com.example.suit_team1.ui.logine

import android.content.Context
import com.example.suit_team1.data.local.Db
import com.example.suit_team1.ui.login.LoginView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(context: Context, private val view: LoginView) {

    val db = Db.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            view.onErrorLogin()
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val status = db?.userDao()?.getLogin(email, password) ?:0
                if(email == status &&)

            }
        }
    }
}

