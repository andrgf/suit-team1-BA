package com.example.gamesuit.activity.ui.menu


import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.until.App


class PresenterMenu(private val view: MenuActivity) {
    private val idUser = AppSharedPreference.id!!

    fun getDataUser() =
        App.appDb.getUserDao().getUserByIdNoLiveData(idUser)
}