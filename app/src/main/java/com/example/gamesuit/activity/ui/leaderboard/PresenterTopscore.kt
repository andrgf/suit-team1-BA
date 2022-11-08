package com.binar.gamesuit.ui.leaderboard


import com.example.gamesuit.activity.ui.leaderboard.TopscoreActivity
import com.example.gamesuit.until.App

class PresenterTopscore(private val view: TopscoreActivity) {
    fun getData() = App.appDb.getUserDao().getUsers()
}