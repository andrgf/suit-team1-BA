package com.example.gamesuit.activity.ui.home

import androidx.lifecycle.LiveData
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.until.App

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    override fun getUser(id: Int): LiveData<User> {
        return App.appDb.getUserDao().getUserById(id)
    }

}