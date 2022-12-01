package com.example.gamesuit.activity.ui.home

import androidx.lifecycle.LiveData
import com.example.gamesuit.activity.data.db.user.User

interface HomeContract {

    interface View
    interface Presenter {
        fun getUser(id: Int): LiveData<User>
    }
}