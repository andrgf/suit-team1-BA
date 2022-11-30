package com.example.gamesuit.activity.ui.home

import androidx.lifecycle.LiveData

interface HomeContract {

    interface View
    interface Presenter {
        fun getUser(id: Int): LiveData<User>
    }
}