package com.example.twittercloneapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class FirebaseAuthRepository @Inject constructor(
    private val authentication: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    @ApplicationContext private val appContext: Context
) {
    private val reference: DatabaseReference = firebaseDatabase.reference
    private val dbRef: DatabaseReference  = reference.child("users")

    var userLiveData: MutableLiveData<FirebaseUser>? = MutableLiveData()

    fun createUser(name: String, email: String, phone: String, password: String) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful) {
                    uploadUserData(name, email, phone)
                } else {
                    Toast.makeText(
                        appContext.applicationContext,
                        "Registration Failure: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show();
                    userLiveData?.postValue(null)
                }
            })
    }

    private fun uploadUserData(name: String, email: String, phone: String) {
        val key = dbRef.push().key
        key?.let { id ->
            var  user  = HashMap<String, String>()
            user["id"] = id
            user["name"] = name
            user["phone"] = phone
            user["email"] = email

            dbRef.child(id).setValue(user).addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful)  {
                    userLiveData?.postValue(authentication.currentUser)
                } else {
                    Toast.makeText(
                        appContext.applicationContext,
                        "Registration Failure: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show();
                    userLiveData?.postValue(null)
                }
            })
        }

    }
}