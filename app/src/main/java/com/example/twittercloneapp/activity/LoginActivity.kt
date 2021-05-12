

package com.example.twittercloneapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.twittercloneapp.R
import androidx.lifecycle.Observer
import com.example.twittercloneapp.commons.ProgressDialog
import com.example.twittercloneapp.extensions.isValidEmail
import com.example.twittercloneapp.viewmodel.FirebaseAuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etEmail
import kotlinx.android.synthetic.main.activity_signup.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val authViewModel: FirebaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
    }

    private fun initViews() {
        createAccount.setOnClickListener {
            navigateToSignInActivity()
        }

        showProgressBar()

        btn_login_with_otp.setOnClickListener {
            if (isValidData()) {
                authViewModel.loginUser(etEmail.text.toString(), etpassword.text.toString())
            }
        }

        authViewModel.loginUserLiveData?.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "User ${it.email}  Logged in succesfuuly", Toast.LENGTH_LONG)
                    .show()
                navigateToMainActivity()
            }
            authViewModel.progress.value = false
        })
    }

    private fun showProgressBar() {
        val dialog =  ProgressDialog.dialog(this, "Logging in...")
        authViewModel.progress.observe(this, Observer { showing ->
            if (showing) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        })
    }

    private fun isValidData(): Boolean {
       if (etEmail.text.isNullOrEmpty() || !etEmail.text.toString().isValidEmail()) {
            etEmail.error = "Please enter the valid email"
            return false
        } else if (etpassword.text.isNullOrEmpty() || etpassword.text!!.length < 6) {
           etpassword.error = "Password Length should be greater than 6"
            return false
        }
        return true
    }

    private fun navigateToSignInActivity() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}