package com.example.gamesuit.activity.ui.onboarding.fragment3


import com.example.gamesuit.until.App

class PresenterFragment3(private val view: OnBoarding3Fragment) {
    fun getData() = App.appDb.getUserDao().getUsers()
}