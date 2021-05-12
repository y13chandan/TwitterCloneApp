package com.example.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twittercloneapp.R
import com.example.twittercloneapp.commons.TwitterCloneAppData
import com.example.twittercloneapp.model.Tweet
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.row_layout_tweet.view.*

interface TweetAdapterListener {
    fun onUpdateTweetTapped(tweet: Tweet)
    fun onDeleteTweetTapped(tweet: Tweet)
}
class TweetAdapter(private val tweets: List<Tweet>, private val listener: TweetAdapterListener): RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetAdapter.ViewHolder, position: Int) {
        holder.tvTweet.text = tweets[position].tweetText
        holder.userName.text = tweets[position].userData?.name
        if (TwitterCloneAppData.getUser()?.id == tweets[position].userData?.id) {
            holder.btnUpdateTweet.visibility = View.VISIBLE
            holder.btnDeleteTweet.visibility = View.VISIBLE
        }
        holder.btnUpdateTweet.setOnClickListener { listener.onUpdateTweetTapped(tweets[position]) }
        holder.btnDeleteTweet.setOnClickListener { listener.onDeleteTweetTapped(tweets[position]) }
    }


    override fun getItemCount(): Int = tweets.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvTweet: AppCompatTextView = v.tvTweet
        val userName: AppCompatTextView = v.tvName
        val btnUpdateTweet: AppCompatImageButton = v.btnUpdateTweet
        val btnDeleteTweet: AppCompatImageButton = v.btnDeleteTweet
    }
}