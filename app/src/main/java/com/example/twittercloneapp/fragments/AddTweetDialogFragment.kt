package com.example.twittercloneapp.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.twittercloneapp.R
import kotlinx.android.synthetic.main.fragment_add_tweet_dialog.*

interface OnAddTweetListener {
    fun onTweetTapped(tweetString: String)
}

class AddTweetDialogFragment : DialogFragment() {
    var listener: OnAddTweetListener? = null


    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_tweet_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        btnCancel.setOnClickListener { dismiss() }
        btnAddTweet.setOnClickListener { listener?.onTweetTapped(etTweet.text.toString()) }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddTweetDialogFragment()
    }
}