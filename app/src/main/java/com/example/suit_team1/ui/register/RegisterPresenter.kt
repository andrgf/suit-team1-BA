package com.example.suit_team1.ui.register

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import com.example.suit_team1.data.local.Db
import com.example.suit_team1.data.model.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterPresenter(context: Context, private val view: RegisterView) {

    private val db = Db.getInstance(context)

    @OptIn(DelicateCoroutinesApi::class)
    fun register(
        firstName: String,
        lastName: String,
        email: String,
        createPwd: String,
        confirmPwd: String,
        user: User
    ) {
        if (firstName.isEmpty() ||
            lastName.isEmpty() ||
            email.isEmpty() ||
            createPwd.isEmpty() ||
            createPwd != confirmPwd
        ) {
            Log.e("Error", "Registrasi Gagal")
            view.onErrorRegister()
        } else {
            Log.d("Success", "Registrasi Behasil")
            GlobalScope.launch(Dispatchers.IO) {
                val status = db?.userDao()?.insert(user) ?: 0

                if (status > 1) {
                    launch(Dispatchers.Main) {
                        view.onSuccessRegister()
                    }
                }
            }
        }
    }
}
