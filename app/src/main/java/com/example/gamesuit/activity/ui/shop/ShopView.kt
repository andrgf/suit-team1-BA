package com.example.gamesuit.activity.ui.shop

import androidx.lifecycle.LiveData
import com.example.gamesuit.activity.data.db.user.User

interface ShopView {
    interface View
    interface Presenter {
        fun getUser(id: Int): LiveData<User>
    }
}