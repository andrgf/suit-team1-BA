package com.example.gamesuit.activity.ui.login

import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.until.App
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun onLogin(email: String, password: String) {
        GlobalScope.launch {
            val dao = App.appDb.getUserDao()
            val getUserAndEmail = dao.getUserByEmailAndPassword(email = email, password = password)
            launch(Dispatchers.Main) {
                if (getUserAndEmail == null) {
                    view.onSignInMsg("Incorrect email or password")
                } else {
                    AppSharedPreference.id = getUserAndEmail.id
                    AppSharedPreference.isLogin = true
                    view.onSuccess(getUserAndEmail)
                }
            }
        }
    }
}