package com.example.twittercloneapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twittercloneapp.repository.HomeRepository
import javax.inject.Inject

class HomeViewModel@Inject constructor(private val repository: HomeRepository) : ViewModel() {

}