package com.example.gamesuit.activity.ui.profil

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.gamesuit.activity.data.local.AppSharedPreference.isLogin
import com.example.gamesuit.activity.ui.login.LoginActivity
import com.example.gamesuit.databinding.FragmentDialogLogoutBinding

class DialogLogout : DialogFragment() {
    private lateinit var binding: FragmentDialogLogoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentDialogLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            isLogin = false
            Intent(activity,LoginActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
    companion object{
        const val DIALOG_LOGOUT = "dialog_logout"
    }
}