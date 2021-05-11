package com.example.twittercloneapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.twittercloneapp.commons.Constants
import com.example.twittercloneapp.commons.TwitterCloneAppData
import com.example.twittercloneapp.model.Tweet
import com.example.twittercloneapp.model.UserData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class FirebaseAuthRepository @Inject constructor(
    private val authentication: FirebaseAuth,
    private val db: FirebaseFirestore,
    @ApplicationContext private val appContext: Context
) {
    var userLiveData: MutableLiveData<FirebaseUser>? = MutableLiveData()
    var loginUserLiveData: MutableLiveData<FirebaseUser>? = MutableLiveData()


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


    fun loginUser(email: String, password: String) {
        authentication.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = authentication.currentUser
                    user?.uid?.let {
                        db.collection(Constants.USERS)
                            .get()
                            .addOnSuccessListener {  result ->
                                for (document in result) {
                                    if (document.get("id") == it) {
                                        var userData = document.toObject(UserData::class.java)
                                        TwitterCloneAppData.setUserData(userData)
                                        break
                                    }
                                }
                            }
                    }
                    loginUserLiveData?.postValue(user)
                } else {
                    Toast.makeText(
                        appContext.applicationContext,
                        "Login Failure: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show();
                    userLiveData?.postValue(null)
                }
            }

    }

    private fun uploadUserData(name: String, email: String, phone: String) {
        var  user  = HashMap<String, String>()
        authentication.currentUser?.uid?.let {
            user["id"] = it
            user["name"] = name
            user["phone"] = phone
            user["email"] = email

            db.collection(Constants.USERS)
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("firebaseRepository", "DocumentSnapshot added with ID: ${documentReference.id}")
                    var userData: UserData = UserData(it, name, phone, email)
                    TwitterCloneAppData.setUserData(userData)
                    userLiveData?.postValue(authentication.currentUser)
                }
                .addOnFailureListener { e ->
                    Log.w("firebaseRepository", "Error adding document", e)
                    Toast.makeText(
                        appContext.applicationContext,
                        "Registration Failure: " + e.message,
                        Toast.LENGTH_SHORT
                    ).show();
                    userLiveData?.postValue(null)
                }
        }

    }
}