package com.example.gamesuit.activity.ui.setting.background

import android.app.Application


class InitApplication : Application() {
    var isNightModeEnabled = false

    override fun onCreate() {
        super.onCreate()
        singleton = this
    }

    fun setIsNightModeEnabled(b: Boolean) {
        TODO("Not yet implemented")
    }

    companion object {
        const val NIGHT_MODE = "NIGHT_MODE"
        private var singleton: InitApplication? = null
        val instance: InitApplication?
            get() {
                if (singleton == null) {
                    singleton = InitApplication()
                }
                return singleton
            }
    }
}