package com.example.twittercloneapp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twittercloneapp.R
import com.example.twittercloneapp.adapters.TweetAdapter
import com.example.twittercloneapp.adapters.TweetAdapterListener
import com.example.twittercloneapp.model.Tweet
import com.example.twittercloneapp.viewmodel.FirebaseAuthViewModel
import com.example.twittercloneapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : Fragment(), OnAddTweetListener, TweetAdapterListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private val authViewModel: FirebaseAuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fab.setOnClickListener { showAddTweetDialog() }
    }

    private fun initViews() {
        rv_tweets.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        homeViewModel.getTweets()
        homeViewModel.tweetListLiveData?.observe(viewLifecycleOwner, Observer { tweets ->
            if (tweets == null) {

            } else {
                if (tweets.isNotEmpty()) {
                    rv_tweets.adapter = TweetAdapter(tweets, this)
                }
            }
        })
    }

    private fun showAddTweetDialog() {
        openDialog(false, null, null)
    }

    override fun onUpdateTweetTapped(tweet: Tweet) {
        openDialog(true, tweet.id, tweet.tweetText)
    }

    override fun onDeleteTweetTapped(tweet: Tweet) {
        AlertDialog.Builder(context)
            .setTitle("Delete Tweet")
            .setMessage("Are you sure you want to delete this tweet?")
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                tweet.id?.let { homeViewModel.deleteTweet(it) }
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            .show()
    }


    override fun onAddTweet(tweetString: String) {
        homeViewModel.addTweet(tweetString)
        Log.d("HomeFragment", tweetString)
    }

    override fun onUpdateTweet(tweetString: String, tweetId: String) {
        homeViewModel.updateTweet(tweetString, tweetId)
    }


    private fun openDialog(isForUpdate: Boolean, tweetId: String?, tweetString: String?) {
        val transaction = childFragmentManager.beginTransaction()
        val fragment = AddTweetDialogFragment.newInstance(isForUpdate, tweetId, tweetString)
        fragment.listener = this
        fragment.show(transaction, "add tweet")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }



}