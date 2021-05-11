package com.example.twittercloneapp.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.twittercloneapp.commons.Constants
import com.example.twittercloneapp.commons.TwitterCloneAppData
import com.example.twittercloneapp.model.Tweet
import com.example.twittercloneapp.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeRepository@Inject constructor(
    private val authentication: FirebaseAuth,
    private val db: FirebaseFirestore,
    @ApplicationContext private val appContext: Context
) {
    var user: UserData? = null
    var tweets: MutableLiveData<List<Tweet>> = MutableLiveData()

    fun getTweets()  {
        db.collection(Constants.TWEETS)
                .get()
                .addOnSuccessListener { result ->
                    val tweetList = ArrayList<Tweet>()
                    for (document in result) {
                        Log.d("Homerepository", "${document.id} => ${document.data}")
                        var tweet = document.toObject(Tweet::class.java)
                        tweet.id = document.id
                        tweetList.add(tweet)
                    }
                    tweets.postValue(tweetList)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(
                            appContext.applicationContext,
                            "Error getting documents. " + exception.message,
                            Toast.LENGTH_SHORT
                    ).show();
                    tweets.postValue(null)
                }

    }

    fun addTweet(tweetString: String) {

        TwitterCloneAppData.getUser()?.let {
            var tweet = HashMap<String, Any>()
            tweet["userData"] = it
            tweet["tweetText"] = tweetString
            tweet["timestamp"] = FieldValue.serverTimestamp()

            db.collection(Constants.TWEETS)
                .add(tweet)
                .addOnSuccessListener {
                    Log.d("Homerepository", "DocumentSnapshot added with ID: ${it.id}")
                }
        }

    }

    fun updateTweet(tweetText: String, id: String) {

    }

}