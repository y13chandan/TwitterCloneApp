package com.example.twittercloneapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twittercloneapp.model.Tweet
import com.example.twittercloneapp.repository.HomeRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val repository: HomeRepository) : ViewModel() {
    var tweetListLiveData: MutableLiveData<List<Tweet>>? = repository.tweets
    val progress = MutableLiveData<Boolean>()

    fun getTweets() {
        repository.getTweets()
    }

    fun addTweet(tweetText: String) {
        repository.addTweet(tweetText)
    }

    fun updateTweet(tweetText: String, id: String) {
        repository.updateTweet(tweetText, id)
    }

    fun deleteTweet(id: String) {
        repository.deleteTweet(id)
    }
}