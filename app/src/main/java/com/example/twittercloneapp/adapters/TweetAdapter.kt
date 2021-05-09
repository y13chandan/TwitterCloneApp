package com.example.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twittercloneapp.R
import com.example.twittercloneapp.model.Tweet
import kotlinx.android.synthetic.main.row_layout_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>): RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetAdapter.ViewHolder, position: Int) {
        holder.tvTweet.text = tweets[position].tweetText
        holder.userName.text = tweets[position].userName
    }

    override fun getItemCount(): Int = tweets.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvTweet = v.tvTweet
        val userName = v.tvName
    }
}