package com.example.twittercloneapp.commons

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.twittercloneapp.R
import kotlinx.android.synthetic.main.progress_dialog.view.*

class ProgressDialog  {
    companion object {
        fun dialog(context: Context, text: String?): Dialog{
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            text?.let {
                val textView = inflate.tvLoading
                textView.text = text
            }
            dialog.setContentView(inflate)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }
    }
}