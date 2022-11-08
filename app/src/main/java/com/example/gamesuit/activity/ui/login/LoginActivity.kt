package com.example.gamesuit.activity.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.gamesuit.activity.data.db.user.User
import com.example.gamesuit.activity.ui.home.HomeActivity
import com.example.gamesuit.activity.ui.registrasi.RegistrasiActivity
import com.example.gamesuit.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var loginBtn: Button
    private lateinit var loadingInd: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = binding.tietEmail
        password = binding.tietPassword
        loginBtn = binding.btnSignIn
        loadingInd = binding.loadingInd

        loginPresenter = LoginPresenter(this)

        binding.btnRegistrasi.setOnClickListener {
            val intent = Intent(this, RegistrasiActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            showProgress()
            Handler(Looper.getMainLooper()).postDelayed({
                loginPresenter.onLogin(
                    email = email.text.toString(),
                    password = password.text.toString()
                )
            }, 3000)
        }

        addTextChangedListenerOnView(email, password, textWatcher = textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val emailText = email.text?.trim().toString()
            val passwordText = password.text?.trim().toString()
            loginBtn.isEnabled =
                (emailText.isNotBlank() && passwordText.isNotBlank())
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun addTextChangedListenerOnView(
        vararg views: TextInputEditText,
        textWatcher: TextWatcher
    ) {
        for (view in views) {
            view.addTextChangedListener(textWatcher)
        }
    }

    override fun onSignInMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        loadingInd.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            loadingInd.visibility = View.GONE
        }, 3000)
    }

    override fun onSuccess(user: User) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        onResetInputField()
    }

    private fun onResetInputField() {
        email.setText("", TextView.BufferType.SPANNABLE)
        password.setText("", TextView.BufferType.SPANNABLE)
    }
}