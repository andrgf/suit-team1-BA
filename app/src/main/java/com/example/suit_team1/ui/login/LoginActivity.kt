package com.example.suit_team1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.suit_team1.MainActivity
import com.example.suit_team1.databinding.ActivityLoginBinding
import com.example.suit_team1.ui.logine.LoginPresenter
import com.example.suit_team1.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = LoginPresenter(this, this)

        binding.btnLogin.setOnClickListener {
            Log.d("Click", "Button login")
            val email = binding.etUsername.text.toString()
            val pwd = binding.etPwd.text.toString()

            presenter.login(email, pwd)
        }

        binding.btnSignUp.setOnClickListener {
            Log.d("Click", "Button Click")
            Intent(this, RegisterActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onSuccessLogin() {
        Intent(this@LoginActivity, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onErrorLogin() {
        Toast.makeText(this, "Masukkan email dan password dengan benar", Toast.LENGTH_SHORT).show()
    }
}