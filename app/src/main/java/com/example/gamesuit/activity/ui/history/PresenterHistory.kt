package com.example.gamesuit.activity.ui.history



import com.example.gamesuit.until.App

class PresenterHistory(private val view: HistoryActivity) {
    fun getData() = App.appDb.getUserDao().getUsers()
}