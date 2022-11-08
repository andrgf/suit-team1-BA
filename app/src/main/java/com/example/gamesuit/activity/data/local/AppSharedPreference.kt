package com.example.gamesuit.activity.data.local

import android.content.Context
import com.example.gamesuit.until.App

object AppSharedPreference {

    private const val APP_SHARED_PREFERENCES = "app_shared_preferences"
    private const val KEY_IS_LOGIN = "key_is_login"
    private const val KEY_ID = "key_id"

    private val sharedPreference =
        App.context.get()?.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    var isLogin: Boolean?
        get() = sharedPreference?.getBoolean(KEY_IS_LOGIN, false)
        set(value) {
            if (value != null) {
                sharedPreference?.edit()?.putBoolean(KEY_IS_LOGIN, value)?.apply()
            }
        }

    var id: Int?
        get() = sharedPreference?.getInt(KEY_ID, 0)
        set(value) {
            if (value != null) {
                sharedPreference?.edit()?.putInt(KEY_ID, value)?.apply()
            }
        }


}