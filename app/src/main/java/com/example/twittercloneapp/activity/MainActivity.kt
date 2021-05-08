package com.example.twittercloneapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.twittercloneapp.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   @Inject lateinit var authentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (authentication.currentUser == null) {
            navigateToLoginActivity()
        } else {
            Toast.makeText(this, "USer is logged In", Toast.LENGTH_LONG).show()
        }
    }

    fun navigateToLoginActivity() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}