package com.example.twittercloneapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twittercloneapp.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(private val repository: FirebaseAuthRepository) : ViewModel() {
    private val userLiveData: MutableLiveData<FirebaseUser>? = null

    fun getUserLiveData(): MutableLiveData<FirebaseUser>? {
        return repository.getUserLiveData()
    }

    fun createUser(name: String, email: String, phone: String, password: String) {
        repository.createUser(name, email, phone, password)
    }

}
