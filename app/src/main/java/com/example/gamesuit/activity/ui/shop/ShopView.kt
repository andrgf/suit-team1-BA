package com.example.gamesuit.activity.ui.shop

import androidx.lifecycle.LiveData

interface ShopView {
    interface View
    interface Presenter {
        fun getUser(id: Int): LiveData<User>
    }


}