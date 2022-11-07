package com.example.suit_team1.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.suit_team1.R
import com.example.suit_team1.data.model.User
import com.example.suit_team1.databinding.ActivityRegisterBinding
import com.example.suit_team1.ui.login.LoginActivity
import java.util.UUID

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = RegisterPresenter(this, this)

        binding.btnUploadPhoto.setOnClickListener {
            Log.e("Click","Button upload photo")

        }

        binding.btnSignUp.setOnClickListener {
            Log.d("Click","Button sign-up")
            val id = UUID.randomUUID().toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val createPwd = binding.etCreatePwd.text.toString()
            val confirmPwd = binding.etConfirmPwd.text.toString()

            val user = User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = confirmPwd
            )
            presenter.register(firstName, lastName, email, createPwd, confirmPwd, user)
        }

        binding.ivBack.setOnClickListener {
            Log.d("Click","Button back")
            finish()
        }
    }

    override fun onSuccessRegister() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Registrasi Berhasil")
        dialog.setMessage("Selamat registrasimu berhasil. Silahkan kembali ke halam Login")
        dialog.setIcon(R.drawable.ic_baseline_check_24)

        dialog.setCancelable(false)
        dialog.setPositiveButton("Back") { dialogInterface, p1 ->
            Log.e("Click","Button back")
            Intent(this@RegisterActivity, LoginActivity::class.java).apply {
                startActivity(this)
            }
        }
        dialog.show()
    }

    override fun onErrorRegister() {
        Toast.makeText(
            this, "Pastikan semua data terisi dengan benar",
            Toast.LENGTH_SHORT
        ).show()
    }

}