package com.example.gamesuit.activity.ui.login

import com.example.gamesuit.activity.data.db.user.User

interface LoginContract {

    interface View {
        fun onSignInMsg(message: String)
        fun showProgress()
        fun onSuccess(user: User)
    }

    interface Presenter {
        fun onLogin(email: String, password: String)
    }

}