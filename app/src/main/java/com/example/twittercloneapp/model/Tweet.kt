package com.example.twittercloneapp.model

import com.google.firebase.Timestamp

data class Tweet(
        var id: String? = null,
        val userData: UserData? = null,
        val tweetText:  String? = null,
        val timestamp: Timestamp?= null
)