package com.example.gamesuit.activity.ui.shop


import androidx.lifecycle.LiveData
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.until.App


class PresenterShop(private val view: ShopActivity) : ShopView.Presenter {

    override fun getUser(id: Int): LiveData<User> {
        return App.appDb.getUserDao().getUserById(id)
    }

}