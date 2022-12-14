package com.example.gamesuit.activity.ui.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.profil.DialogLogout.Companion.DIALOG_LOGOUT
import com.example.gamesuit.activity.ui.profil.DialogUpdate.Companion.DIALOG_UPDATE
import com.example.gamesuit.databinding.ActivityProfilBinding
import com.example.gamesuit.until.AvatarHelper
import com.example.gamesuit.until.grabText

class ProfileActivity: AppCompatActivity(), ProfileView {
    private lateinit var binding: ActivityProfilBinding
    private lateinit var presenterProfile: PresenterProfile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenterProfile = PresenterProfile(this)

        val usernameDb = presenterProfile.getDataUser().username
        val emailDb = presenterProfile.getDataUser().email
        val avatarID = presenterProfile.getDataUser().avatarId
        binding.etUsername.setText(usernameDb)
        binding.etEmail.setText(emailDb)

        val avatar = arrayOf(
            binding.avatarId1,
            binding.avatarId2,
            binding.avatarId3,
            binding.avatarId4,
        )

        AvatarHelper.provideList().forEachIndexed { index, id ->
            if (id == presenterProfile.userAvatar) {
                avatar[index].setBackgroundResource(R.color.biru)
            }
        }

        AvatarHelper.provideList().forEachIndexed { index, id ->
            if (id == avatarID) {
                avatar[index].setBackgroundResource(R.color.biru)
            }
        }
        choiceAvatar(avatar)

        binding.btnUpdate.setOnClickListener {
            val username = binding.etUsername.grabText()
            val email = binding.etEmail.grabText()
            val pass = binding.etOldPass.grabText()
            val newPass = binding.etNewPass.grabText()
            val reNewPass = binding.etRePass.grabText()

            presenterProfile.updateDataUser(
                presenterProfile.userAvatar,
                username,
                email,
                pass,
                newPass,
                reNewPass
            )
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnSignOut.setOnClickListener {
            onSignOut()
        }
    }

    override fun onSuccessUpdate(user: Int) {
        val dialogUpdate = DialogUpdate()
        dialogUpdate.show(supportFragmentManager, DIALOG_UPDATE)
        binding.etOldPass.text?.clear()
        binding.etNewPass.text?.clear()
        binding.etRePass.text?.clear()
    }

    override fun onErrorUpdate(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSignOut() {
        val dialogSignOut = DialogLogout()
        dialogSignOut.show(supportFragmentManager, DIALOG_LOGOUT)
    }

    override fun choiceAvatar(user: Array<ImageView>) {
        user.forEachIndexed { index: Int, imageView: ImageView ->
            imageView.setOnClickListener {
                presenterProfile.userAvatar = AvatarHelper.provideList()[index]
                user.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                user[index].setBackgroundResource(R.color.biru)
            }
        }
    }
}