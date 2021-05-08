package com.example.twittercloneapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class FirebaseAuthRepository @Inject constructor(
    private val authentication: FirebaseAuth,
    @ApplicationContext private val appContext: Context
) {
    private val userLiveData: MutableLiveData<FirebaseUser>? = null


    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return userLiveData
    }

    fun createUser(name: String, email: String, phone: String, password: String) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful) {
                    userLiveData?.postValue(authentication.currentUser)
                } else {
                    Toast.makeText(
                        appContext.applicationContext,
                        "Registration Failure: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show();
                }
            })
    }
}