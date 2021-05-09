package com.example.twittercloneapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.twittercloneapp.R
import com.example.twittercloneapp.extensions.isValidEmail
import com.example.twittercloneapp.viewmodel.FirebaseAuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.etEmail

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private val authViewModel: FirebaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btnRegister.setOnClickListener {
            if (isValidData()) {
                authViewModel.createUser(
                    etName.text.toString(),
                    etEmail.text.toString(),
                    etPhone.text.toString(),
                    etPassword.text.toString()
                )
            }
        }
        showProgressBar()

        authViewModel.userLiveData?.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "User ${it.email}  Logged in succesfuuly", Toast.LENGTH_LONG)
                    .show()
                navigateToMainActivity()
            }
            authViewModel.progress.value = false
        })

    }

    private fun showProgressBar() {
        authViewModel.progress.observe(this, Observer { showing ->
            if (showing) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }

    private fun isValidData(): Boolean {
        if (etName.text.isNullOrEmpty() || etName.text.toString().length <= 3) {
            etName.error = "Please enter the valid name4"
            return false
        } else if (etEmail.text.isNullOrEmpty() || !etEmail.text.toString().isValidEmail()) {
            etEmail.error = "Please enter the valid email"
            return false
        } else if (etPhone.text.isNullOrEmpty() || etPhone.text.toString().length != 10) {
            etPhone.error = "Please enter the valid phone number"
            return false
        } else if (etPassword.text.isNullOrEmpty() || etPassword.text!!.length < 6) {
            etPassword.error = "Password Length should be greater than 6"
            return false
        } else if (etConfirmPassword.text.isNullOrEmpty() || (etPassword.text.toString() != etConfirmPassword.text.toString())) {
            etConfirmPassword.error = "Passwords Should Match"
            return false
        }
        return true
    }

    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
