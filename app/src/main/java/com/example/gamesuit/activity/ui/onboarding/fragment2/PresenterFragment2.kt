package com.example.gamesuit.activity.ui.onboarding.fragment2


import com.example.gamesuit.until.App

class PresenterFragment2(private val view: OnBoarding2Fragment) {
    fun getData() = App.appDb.getUserDao().getUsers()
}