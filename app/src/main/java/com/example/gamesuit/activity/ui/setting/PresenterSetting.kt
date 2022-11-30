package com.example.gamesuit.activity.ui.setting

import com.example.gamesuit.activity.data.local.AppSharedPreference
import com.example.gamesuit.until.App

class PresenterSetting (private val view: SettingActivity) {
    private val idUser = AppSharedPreference.id!!

    fun getDataUser() =
        App.appDb.getUserDao().getUserByIdNoLiveData(idUser)
}