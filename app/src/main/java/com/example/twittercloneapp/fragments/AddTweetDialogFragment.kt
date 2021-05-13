package com.example.twittercloneapp.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import com.example.twittercloneapp.R
import kotlinx.android.synthetic.main.fragment_add_tweet_dialog.*

interface OnAddTweetListener {
    fun onAddTweet(tweetString: String)
    fun onUpdateTweet(tweetString: String, tweetId: String)
}

private const val IS_FOR_UPDATE = "isForUpdate"
private const val TWEET_ID = "id"
private const val TWEET_STRING = "tweetString"

class AddTweetDialogFragment : DialogFragment() {
    var listener: OnAddTweetListener? = null
    private var isForUpdate: Boolean? = false
    private var tweetId: String? = null
    private var tweetString: String? = null

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isForUpdate = it.getBoolean(IS_FOR_UPDATE)
            tweetId = it.getString(TWEET_ID)
            tweetString = it.getString(TWEET_STRING)
        }
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
        etTweet.doAfterTextChanged {
            it?.let {
                if (it.toString().length > 280) {
                    context?.let { ctx -> etTweet.setTextColor(ctx.getColor(R.color.red)) }
                    Toast.makeText(context, "You have crossed the maximum text limit is 280", Toast.LENGTH_LONG).show()
                } else {
                    context?.let { ctx -> etTweet.setTextColor(ctx.getColor(R.color.grey_dark)) }
                }
            }
        }
        if (isForUpdate == true) {
            btnAddTweet.text = "Update"
            etTweet.setText(tweetString)
        }
        btnAddTweet.setOnClickListener {
            if (etTweet != null && !etTweet.text.isNullOrEmpty() && etTweet.text?.length!! <= 280) {
                if (isForUpdate == true) {
                    tweetId?.let { it1 -> listener?.onUpdateTweet(etTweet.text.toString(), it1) }
                } else {
                    listener?.onAddTweet(etTweet.text.toString())
                }
            } else {
                Toast.makeText(context, "Please enter the valid tweet", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(isForUpdate: Boolean, tweetId: String?, tweetString: String?) =
            AddTweetDialogFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_FOR_UPDATE, isForUpdate)
                    putString(TWEET_ID, tweetId)
                    putString(TWEET_STRING, tweetString)
                }
            }
    }
}