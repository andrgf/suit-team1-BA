package com.example.gamesuit.activity.ui.registrasi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityRegistrasiBinding
import com.google.android.material.textfield.TextInputEditText

@SuppressLint("UseCompatLoadingForDrawables")
class RegistrasiActivity : AppCompatActivity(), RegistrasiContract.View {
    private lateinit var binding: ActivityRegistrasiBinding
    private lateinit var registrasiPresent: RegistrasiPresenter
    private lateinit var userName: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var rePassword: TextInputEditText
    private lateinit var btnRegistrasi: Button
    private lateinit var loadingInd: ProgressBar
    private lateinit var radioGroup: RadioGroup
    private lateinit var avatar1: RadioButton
    private var avatarId: Int = R.drawable.avatar21


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrasiPresent = RegistrasiPresenter(this)

        userName = binding.etUsername
        email = binding.etEmail
        password = binding.etPassword
        rePassword = binding.etRepassword
        btnRegistrasi = binding.btnRegistrasi
        loadingInd = binding.loadingInd
        radioGroup = binding.radioGroup
        avatar1 = binding.avatar1

        btnRegistrasi.setOnClickListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString() != rePassword.text.toString()) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            } else {
                showProgress()
                Handler(Looper.getMainLooper()).postDelayed({
                    registrasiPresent.register(
                        username = userName.text.toString(),
                        email = email.text.toString(),
                        password = password.text.toString(),
                        avatarId = avatarId
                    )
                }, 3000)
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.avatar1.id -> {
                    avatarId = R.drawable.avatar21
                    binding.avatar1.background = getDrawable(R.drawable.avatar11)
                    binding.avatar2.background = getDrawable(R.drawable.avatar22)
                    binding.avatar3.background = getDrawable(R.drawable.avatar23)
                    binding.avatar4.background = getDrawable(R.drawable.avatar24)
                }
                binding.avatar2.id -> {
                    avatarId = R.drawable.avatar22
                    binding.avatar1.background = getDrawable(R.drawable.avatar21)
                    binding.avatar2.background = getDrawable(R.drawable.avatar12)
                    binding.avatar3.background = getDrawable(R.drawable.avatar23)
                    binding.avatar4.background = getDrawable(R.drawable.avatar24)
                }
                binding.avatar3.id -> {
                    avatarId = R.drawable.avatar23
                    binding.avatar1.background = getDrawable(R.drawable.avatar21)
                    binding.avatar2.background = getDrawable(R.drawable.avatar22)
                    binding.avatar3.background = getDrawable(R.drawable.avatar13)
                    binding.avatar4.background = getDrawable(R.drawable.avatar24)
                }
                binding.avatar4.id -> {
                    avatarId = R.drawable.avatar24
                    binding.avatar1.background = getDrawable(R.drawable.avatar21)
                    binding.avatar2.background = getDrawable(R.drawable.avatar22)
                    binding.avatar3.background = getDrawable(R.drawable.avatar23)
                    binding.avatar4.background = getDrawable(R.drawable.avatar14)
                }
            }
        }

        addTextChangedListenerOnView(
            userName,
            email,
            password,
            rePassword,
            textWatcher = textWatcher
        )

    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val userNameText = userName.text?.trim().toString()
            val emailText = email.text?.trim().toString()
            val passwordText = password.text?.trim().toString()
            val rePasswordText = rePassword.text?.trim().toString()
            btnRegistrasi.isEnabled =
                (userNameText.isNotBlank() && emailText.isNotBlank() && passwordText.isNotBlank() && rePasswordText.isNotBlank())
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


    override fun onSignUpMsg(message: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }, 0)
    }

    override fun showProgress() {
        loadingInd.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            loadingInd.visibility = View.GONE
        }, 3000)
    }


    override fun onSuccess(username: String) {
        registrasiPresent.getUser(email.text.toString(), password.text.toString())
        val intent = Intent(this, RegisterConfirmationActivity::class.java)
        startActivity(intent)
        onResetInputField()
    }

    private fun onResetInputField() {
        userName.setText("", TextView.BufferType.SPANNABLE)
        email.setText("", TextView.BufferType.SPANNABLE)
        password.setText("", TextView.BufferType.SPANNABLE)
        rePassword.setText("", TextView.BufferType.SPANNABLE)
        avatar1.background = getDrawable(R.drawable.avatar11)
        avatarId = R.drawable.avatar21
    }
}